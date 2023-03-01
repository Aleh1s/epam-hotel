package ua.aleh1s.hotelepam.service.impl;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public RoomServiceImpl(
            ReservationRepository reservationRepository,
            RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
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
    public List<RoomEntity> getAvailableRooms(Integer guests, Period requestedPeriod) {
        List<ReservationEntity> actualReservations = reservationRepository.getActualReservations();

        Map<Integer, List<ReservationEntity>> groupByRoomNumber = actualReservations.stream()
                .collect(Collectors.groupingBy(ReservationEntity::getRoomNumber));

        Set<Integer> busyRooms = groupByRoomNumber.entrySet().stream()
                .filter(entry -> isRoomBusy(requestedPeriod, entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return roomRepository.getAllByGuests(guests).stream()
                .filter(room -> !busyRooms.contains(room.getRoomNumber()))
                .toList();
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

    @Override
    public List<RoomEntity> getSortedRooms(Map<String, String> sortParamMap) {
        List<RoomEntity> roomList = roomRepository.getAll();
        if (sortParamMap.containsKey("price")) {
            String direction = sortParamMap.get("price");
            Comparator<RoomEntity> comparator = Comparator.comparing(RoomEntity::getPrice);
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        if (sortParamMap.containsKey("guests")) {
            String direction = sortParamMap.get("guests");
            Comparator<RoomEntity> comparator = Comparator.comparingInt(RoomEntity::getPersonsNumber);
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        if (sortParamMap.containsKey("class")) {
            String direction = sortParamMap.get("class");
            Comparator<RoomEntity> comparator = Comparator.comparingInt(room -> room.getRoomClass().getIndex());
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        if (sortParamMap.containsKey("status")) {
            String direction = sortParamMap.get("status");
            Comparator<RoomEntity> comparator = Comparator.comparingInt(room -> room.getStatus().getIndex());
            if (direction.equals("desc"))
                comparator = comparator.reversed();
            roomList.sort(comparator);
        }

        return roomList;
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
