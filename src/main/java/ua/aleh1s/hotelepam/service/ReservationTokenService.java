package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;

public interface ReservationTokenService {

    void create(ReservationTokenEntity reservationToken);
    void confirmToken(ReservationTokenEntity reservationToken);
    ReservationTokenEntity getById(String tokenId);
}
