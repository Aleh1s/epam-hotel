package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.criteria.Order;
import ua.aleh1s.hotelepam.model.criteria.RoomCriteria;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static ua.aleh1s.hotelepam.model.criteria.Order.DESC;

@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Override
    public void update(RoomEntity room) {
        roomRepository.update(room);
    }

    @Override
    public boolean isRoomAvailable(Integer number, Period period) {
        List<ReservationEntity> actualReservations =
                reservationRepository.getActualReservationsByRoomNumber(number);

        return actualReservations.stream()
                .map(it -> new Period(it.getCheckIn(), it.getCheckOut()))
                .noneMatch(it -> it.intersects(period));
    }

    @Override
    public Page<RoomEntity> getAvailableRooms(RoomCriteria criteria, PageRequest pageRequest) {
        List<ReservationEntity> actualReservations = reservationRepository.getActualReservations();

        Map<Integer, List<ReservationEntity>> groupByRoomNumber = actualReservations.stream()
                .collect(Collectors.groupingBy(ReservationEntity::getRoomNumber));

        Set<Integer> busyRooms = groupByRoomNumber.entrySet().stream()
                .filter(entry -> isRoomBusy(criteria.getPeriod(), entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        List<RoomEntity> availableRooms = roomRepository.getAll().stream()
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
        if (Objects.nonNull(price)) {
            comparator = Comparator.comparing(RoomEntity::getPrice);
            if (price.equals(DESC))
                comparator = comparator.reversed();
        } else if (Objects.nonNull(guests)) {
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
    public BigDecimal getTotalPrice(Integer roomNumber, Period requestedPeriod) {
        RoomEntity room = getByRoomNumber(roomNumber);
        return getTotalPrice(room.getPrice(), requestedPeriod);
    }

    @Override
    public BigDecimal getTotalPrice(RoomEntity room, Period requestedPeriod) {
        return getTotalPrice(room.getPrice(), requestedPeriod);
    }

    @Override
    public RoomEntity getByRoomNumber(Integer roomNumber) {
        return roomRepository.getByRoomNumber(roomNumber)
                .orElseThrow(ApplicationException::new);
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
}
