package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.ReservationEntity;

public interface PaymentService {
    ReservationEntity payReservation(Long reservationId, Long userId);
}
