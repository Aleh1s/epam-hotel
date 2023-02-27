package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface ReservationService {
    Long create(ReservationEntity entity);
    ReservationEntity getById(Long id);
    void update(ReservationEntity entity);
    void changeStatus(ReservationEntity entity, ReservationStatus status);
    Page<ReservationEntity> getAllByStatus(ReservationStatus status, PageRequest pageRequest);
    Page<ReservationEntity> getAll(PageRequest pageRequest);
    Page<ReservationEntity> getAllByUserIdAndStatusOrderByCreatedAtDesc(Long userId, ReservationStatus status, PageRequest pageRequest);
    Page<ReservationEntity> getAllByUserIdOrderByCreatedAtDesc(Long userId, PageRequest pageRequest);
}
