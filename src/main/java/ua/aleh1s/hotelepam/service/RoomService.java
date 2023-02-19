package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class RoomService {

    public boolean isRoomAvailable(Integer number, Period period) {
        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();

        List<ReservationEntity> actualReservations =
                reservationRepository.getActualReservationsByRoomNumber(number);

        return actualReservations.stream()
                .map(it -> new Period(it.getEntryDate(), it.getLeavingDate()))
                .noneMatch(it -> it.intersects(period));
    }

    public List<RoomEntity> getAvailableRooms(Integer guests, Period requestedPeriod) {
        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();

        List<ReservationEntity> allReservations = reservationRepository.getActualReservations();

        Map<Integer, List<ReservationEntity>> groupByRoomNumber = allReservations.stream()
                .collect(Collectors.groupingBy(ReservationEntity::getRoomNumber));

        Set<Integer> busyRooms = groupByRoomNumber.entrySet().stream()
                .filter(entry -> isRoomBusy(requestedPeriod, entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return roomRepository.getAll().stream()
                .filter(room -> !busyRooms.contains(room.getRoomNumber()))
                .filter(room -> Objects.equals(room.getPersonsNumber(), guests))
                .toList();
    }

    private boolean isRoomBusy(Period requestedPeriod, List<ReservationEntity> roomReservations) {
        return roomReservations.stream()
                .map(it -> new Period(it.getEntryDate(), it.getLeavingDate()))
                .anyMatch(it -> it.intersects(requestedPeriod));
    }

    public BigDecimal getTotalPrice(RoomEntity room, Period requestedPeriod) {
        BigDecimal roomPrice = room.getPrice();
        int nights = requestedPeriod.getDaysBetween();
        return roomPrice.multiply(BigDecimal.valueOf(nights));
    }

    public Optional<RoomEntity> getByRoomNumber(Integer roomNumber) {
        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        return roomRepository.getByRoomNumber(roomNumber);
    }
}
