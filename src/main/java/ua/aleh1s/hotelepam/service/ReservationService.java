package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

public class ReservationService {

    public Long create(ReservationEntity reservation) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();
        return reservationRepository.create(reservation);
    }

    public ReservationEntity getById(Long id) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();

        return reservationRepository.getById(id)
                .orElseThrow(ApplicationException::new);
    }

    public void changeStatus(ReservationEntity reservation, ReservationStatus status) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();

        reservation.setStatus(status);
        reservationRepository.updateStatus(reservation);
    }

    public Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();

        return reservationRepository.getAllByStatus(status, pageRequest);
    }

    public Page<ReservationEntity> getAll(PageRequest pageRequest) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();

        return reservationRepository.getAll(pageRequest);
    }

    public List<ReservationEntity> getAllByCustomerId(Long userId) {
        ReservationRepository reservationRepository =
                AppContext.getInstance().getReservationRepository();

        return reservationRepository.getAllByCustomerId(userId);
    }

    public void update(ReservationEntity reservation) {
        ReservationService reservationService =
                AppContext.getInstance().getReservationService();

        reservationService.update(reservation);
    }
}
