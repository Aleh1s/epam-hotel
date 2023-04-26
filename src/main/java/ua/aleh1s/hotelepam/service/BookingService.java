package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.utils.Period;

public interface BookingService {

    ReservationEntity bookRoom(Integer roomNumber, Long customerId, Period requestedPeriod) throws ServiceException;
    ReservationEntity bookRoom(Long requestId) throws ServiceException;
}
