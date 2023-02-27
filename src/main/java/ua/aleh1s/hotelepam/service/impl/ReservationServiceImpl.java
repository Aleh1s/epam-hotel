package ua.aleh1s.hotelepam.service.impl;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long create(ReservationEntity entity) {
        return reservationRepository.create(entity);
    }

    @Override
    public ReservationEntity getById(Long id) {
        return reservationRepository.getById(id)
                .orElseThrow(ApplicationException::new);
    }

    @Override
    public void changeStatus(ReservationEntity entity, ReservationStatus status) {
        entity.setStatus(status);
        reservationRepository.updateStatus(entity);
    }

    @Override
    public Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest) {
        return reservationRepository.getAllByStatus(status, pageRequest);
    }

    @Override
    public Page<ReservationEntity> getAll(PageRequest pageRequest) {
        return reservationRepository.getAll(pageRequest);
    }

    @Override
    public Page<ReservationEntity> getAllByUserIdAndStatusOrderByCreatedAtDesc(Long userId, ReservationStatus status, PageRequest pageRequest) {
        return reservationRepository.getAllByUserIdAndStatusOrderByCreatedAtDesc(userId, status, pageRequest);
    }

    @Override
    public void update(ReservationEntity reservation) {
        reservationRepository.update(reservation);
    }

    @Override
    public Page<ReservationEntity> getAllByUserIdOrderByCreatedAtDesc(Long userId, PageRequest pageRequest) {
        return reservationRepository.getAllByUserIdOrderByCreatedAtDesc(userId, pageRequest);
    }
}
