package ua.aleh1s.hotelepam.repository;

import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;

import java.util.Optional;

public interface ReservationTokenRepository {
    void create(ReservationTokenEntity reservationToken);

    Optional<ReservationTokenEntity> findById(String tokenId);

    void update(ReservationTokenEntity reservationToken);
}
