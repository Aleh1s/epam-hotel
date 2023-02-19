package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.util.Optional;

public class ReservationService {

    public Long create(ReservationEntity reservation) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();
        return reservationRepository.create(reservation);
    }

    public Optional<ReservationEntity> getById(Long id) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();
        return reservationRepository.getById(id);
    }

    public void changeStatus(ReservationEntity reservation, ReservationStatus status) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();

        reservation.setStatus(status);
        reservationRepository.updateStatus(reservation);
    }
}
