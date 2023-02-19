package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationTokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReservationTokenService {

    public void create(ReservationTokenEntity reservationToken) {
        ReservationTokenRepository reservationTokenRepository =
                AppContext.getInstance().getReservationTokenRepository();

        reservationTokenRepository.create(reservationToken);
    }

    public void confirmToken(ReservationTokenEntity reservationToken) {
        ReservationTokenRepository reservationTokenRepository =
                AppContext.getInstance().getReservationTokenRepository();

        reservationToken.setConfirmedAt(LocalDateTime.now());
        reservationTokenRepository.updateConfirmedAt(reservationToken);
    }

    public Optional<ReservationTokenEntity> getById(String tokenId) {
        ReservationTokenRepository reservationTokenRepository =
                AppContext.getInstance().getReservationTokenRepository();

        return reservationTokenRepository.findById(tokenId);
    }
}
