package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationTokenRepository;

import java.time.LocalDateTime;

public class ReservationTokenService {

    private final ReservationTokenRepository reservationTokenRepository;

    public ReservationTokenService(ReservationTokenRepository reservationTokenRepository) {
        this.reservationTokenRepository = reservationTokenRepository;
    }

    public void create(ReservationTokenEntity reservationToken) {
        reservationTokenRepository.create(reservationToken);
    }

    public void confirmToken(ReservationTokenEntity reservationToken) {
        reservationToken.setConfirmedAt(LocalDateTime.now());
        reservationTokenRepository.updateConfirmedAt(reservationToken);
    }

    public ReservationTokenEntity getById(String tokenId) {
        return reservationTokenRepository.findById(tokenId)
                .orElseThrow(ApplicationException::new);
    }
}
