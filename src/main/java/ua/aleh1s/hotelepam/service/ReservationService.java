package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface ReservationService {
    ReservationEntity getById(Long id);
    Long create(ReservationEntity entity);
    void update(ReservationEntity entity);
    void cancelReservation(ReservationEntity reservation);
    Page<ReservationEntity> getAllActualPayedReservations(PageRequest pageRequest);
    Page<ReservationEntity> getAllReservationsByUserId(Long userId, PageRequest pageRequest);
}
