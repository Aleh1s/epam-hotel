package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.utils.Period;

import java.util.List;

public class RoomService {

    public boolean isRoomAvailable(Integer number, Period period) {
        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();

        List<ReservationEntity> actualReservations =
                reservationRepository.getActualReservationsByRoomNumber(number);

        return actualReservations.stream()
                .map(it -> new Period(it.getEntryDate(), it.getLeavingDate()))
                .noneMatch(it -> it.intersects(period));
    }
}
