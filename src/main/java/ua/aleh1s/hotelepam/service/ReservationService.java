package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface ReservationService {
    Long create(ReservationEntity entity);
    ReservationEntity getById(Long id);
    void update(ReservationEntity entity);
    Page<ReservationEntity> getAllActualPayedReservations(PageRequest pageRequest);
    Page<ReservationEntity> getAllReservationsByUserId(Long userId, PageRequest pageRequest);
    void cancelReservation(ReservationEntity reservation);
}
