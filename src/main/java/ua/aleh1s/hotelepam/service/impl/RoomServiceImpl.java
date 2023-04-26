package ua.aleh1s.hotelepam.service.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.criteria.Order;
import ua.aleh1s.hotelepam.model.criteria.RoomCriteria;
import ua.aleh1s.hotelepam.model.dto.RoomDto;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.repository.ReservationRepository;
import ua.aleh1s.hotelepam.repository.RoomRepository;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.exception.ValidationException;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Period;
import ua.aleh1s.hotelepam.validator.impl.RoomDtoValidator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.model.criteria.Order.DESC;

@AllArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Override
    public void create(RoomEntity room) {
        roomRepository.save(room);
    }

    @Override
    public void create(RoomDto roomDto) throws ServiceException {
        RoomDtoValidator validator = new RoomDtoValidator();
        validator.validate(roomDto);

        if (validator.hasErrors())
            throw new ValidationException(validator.getMessagesByRejectedValue());

        if (roomDto.getImage().length == 0)
            throw new ServiceException("Room image is required!");

        if (existsByRoomNumber(roomDto.getNumber()))
            throw new ServiceException("Room with room number " + roomDto.getNumber() + " already exists.");

        RoomEntity roomEntity = from(roomDto);
        roomEntity.setIsUnavailable(true);

        create(roomEntity);
        updateImage(roomEntity);
    }

    @Override
    public void update(RoomEntity room) {
        roomRepository.update(room);
    }

    @Override
    public void update(RoomDto roomDto) throws ServiceException {
        RoomDtoValidator validator = new RoomDtoValidator();
        validator.validate(roomDto);

        if (validator.hasErrors())
            throw new ValidationException(validator.getMessagesByRejectedValue());

        RoomEntity roomEntity = from(roomDto);
        roomEntity.setIsUnavailable(true);

        update(roomEntity);

        if (roomEntity.getImage().length > 0)
            updateImage(roomEntity);
    }

    @Override
    public void updateImage(RoomEntity room) {
        roomRepository.saveImage(room.getNumber(), room.getImage());
    }

    @Override
    public boolean existsByRoomNumber(Integer roomNumber) {
        return roomRepository.getByRoomNumber(roomNumber).isPresent();
    }

    @Override
    public boolean isRoomAvailable(Integer number, Period period) throws ServiceException {
        RoomEntity room = getByRoomNumber(number);
        if (room.getIsUnavailable())
            return false;

        List<ReservationEntity> actualReservations =
                reservationRepository.getActualReservationsByRoomNumber(number);

        return actualReservations.stream()
                .map(it -> new Period(it.getCheckIn(), it.getCheckOut()))
                .noneMatch(it -> it.intersects(period));
    }

    @Override
    public Page<RoomEntity> getNotReservedRooms(RoomCriteria criteria, PageRequest pageRequest) {
        List<ReservationEntity> actualReservations = reservationRepository.getActualReservations();

        Map<Integer, List<ReservationEntity>> groupByRoomNumber = actualReservations.stream()
                .collect(Collectors.groupingBy(ReservationEntity::getRoomNumber));

        Set<Integer> busyRooms = groupByRoomNumber.entrySet().stream()
                .filter(entry -> isRoomBusy(criteria.getPeriod(), entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        List<RoomEntity> availableRooms = roomRepository.getAvailableRooms().stream()
                .filter(room -> !busyRooms.contains(room.getNumber()))
                .collect(Collectors.toList());

        sortRooms(availableRooms, criteria);

        List<RoomEntity> result = availableRooms.stream()
                .skip(pageRequest.getOffset())
                .limit(pageRequest.getLimit())
                .collect(Collectors.toList());

        return Page.of(result, availableRooms.size());
    }

    @Override
    public Page<RoomEntity> getRooms(PageRequest pageRequest) {
        return roomRepository.getAll(pageRequest);
    }

    private void sortRooms(List<RoomEntity> toSort, RoomCriteria criteria) {
        Order price = criteria.getPrice();
        Order guests = criteria.getGuests();
        Order clazz = criteria.getClazz();

        Comparator<RoomEntity> comparator;
        if (nonNull(price)) {
            comparator = Comparator.comparing(RoomEntity::getPrice);
            if (price.equals(DESC))
                comparator = comparator.reversed();
        } else if (nonNull(guests)) {
            comparator = Comparator.comparing(RoomEntity::getGuests);
            if (guests.equals(DESC))
                comparator.reversed();
        } else {
            comparator = Comparator.comparing(room -> room.getClazz().getIndex());
            if (clazz.equals(DESC))
                comparator.reversed();
        }

        toSort.sort(comparator);
    }

    @Override
    public BigDecimal getTotalPrice(Integer roomNumber, Period requestedPeriod) throws ServiceException {
        RoomEntity room = getByRoomNumber(roomNumber);
        return getTotalPrice(room.getPrice(), requestedPeriod);
    }

    @Override
    public BigDecimal getTotalPrice(RoomEntity room, Period requestedPeriod) {
        return getTotalPrice(room.getPrice(), requestedPeriod);
    }

    @Override
    public RoomEntity getByRoomNumber(Integer roomNumber) throws ServiceException {
        return roomRepository.getByRoomNumber(roomNumber)
                .orElseThrow(() -> new ServiceException("Room with room number " + roomNumber + " does not exist"));
    }

    @Override
    public byte[] getImageByRoomNumber(Integer roomNumber) {
        return roomRepository.getImageByRoomNumber(roomNumber);
    }

    @Override
    public void deleteByNumber(Integer roomNumber) throws ServiceException {
        List<ReservationEntity> actualReservations =
                reservationRepository.getActualReservationsByRoomNumber(roomNumber);

        if (!actualReservations.isEmpty())
            throw new ServiceException("Cannot delete room. It has uncompleted reservations!");

        roomRepository.deleteByNumber(roomNumber);
    }

    private boolean isRoomBusy(Period requestedPeriod, List<ReservationEntity> roomReservations) {
        return roomReservations.stream()
                .map(it -> new Period(it.getCheckIn(), it.getCheckOut()))
                .anyMatch(it -> it.intersects(requestedPeriod));
    }

    private BigDecimal getTotalPrice(BigDecimal pricePerNight, Period requestedPeriod) {
        int nights = requestedPeriod.getDaysBetween();
        return pricePerNight.multiply(BigDecimal.valueOf(nights));
    }

    private RoomEntity from(RoomDto roomDto) {
        return RoomEntity.builder()
                .number(roomDto.getNumber())
                .clazz(roomDto.getClazz())
                .title(roomDto.getTitle())
                .description(roomDto.getDescription())
                .attributes(roomDto.getAttributes())
                .beds(roomDto.getBeds())
                .guests(roomDto.getGuests())
                .area(roomDto.getArea())
                .price(roomDto.getPrice())
                .image(roomDto.getImage())
                .isUnavailable(roomDto.getIsUnavailable())
                .build();
    }
}
