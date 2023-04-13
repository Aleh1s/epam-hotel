package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.utils.Page;
import ua.aleh1s.hotelepam.utils.PageRequest;

public interface ReservationService {
    ReservationEntity getById(Long id) throws ServiceException;
    Long create(ReservationEntity entity) throws ServiceException;
    void update(ReservationEntity entity) throws ServiceException;
    void cancelReservation(ReservationEntity reservation) throws ServiceException;
    Page<ReservationEntity> getAllActualPayedReservations(PageRequest pageRequest) throws ServiceException;
    Page<ReservationEntity> getAllReservationsByUserId(Long userId, PageRequest pageRequest) throws ServiceException;
}
