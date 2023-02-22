package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.pagination.Page;
import ua.aleh1s.hotelepam.model.pagination.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Long create(ReservationEntity reservation) {
        return reservationRepository.create(reservation);
    }

    public ReservationEntity getById(Long id) {
        return reservationRepository.getById(id)
                .orElseThrow(ApplicationException::new);
    }

    public void changeStatus(ReservationEntity reservation, ReservationStatus status) {
        reservation.setStatus(status);
        reservationRepository.updateStatus(reservation);
    }

    public Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) {
        return reservationRepository.getAllByStatus(status, pageRequest);
    }

    public Page<ReservationEntity> getAll(PageRequest pageRequest) {
        return reservationRepository.getAll(pageRequest);
    }

    public List<ReservationEntity> getAllByCustomerId(Long userId) {
        return reservationRepository.getAllByCustomerId(userId);
    }

    public void update(ReservationEntity reservation) {
        reservationRepository.update(reservation);
    }
}
