package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;

public interface ReservationTokenService {

    void create(ReservationTokenEntity reservationToken) throws ServiceException;
    void confirmToken(ReservationTokenEntity reservationToken) throws ServiceException;
    ReservationTokenEntity getById(String tokenId) throws ServiceException;
}
