package ua.aleh1s.hotelepam.service.impl;

import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationTokenRepository;
import ua.aleh1s.hotelepam.service.ReservationTokenService;

import java.time.LocalDateTime;

public class ReservationTokenServiceImpl implements ReservationTokenService {

    private final ReservationTokenRepository reservationTokenRepository;

    public ReservationTokenServiceImpl(ReservationTokenRepository reservationTokenRepository) {
        this.reservationTokenRepository = reservationTokenRepository;
    }

    @Override
    public void create(ReservationTokenEntity reservationToken) {
        reservationTokenRepository.create(reservationToken);
    }

    @Override
    public void confirmToken(ReservationTokenEntity reservationToken) {
        reservationToken.setConfirmedAt(LocalDateTime.now());
        reservationTokenRepository.update(reservationToken);
    }

    @Override
    public ReservationTokenEntity getById(String tokenId) {
        return reservationTokenRepository.findById(tokenId)
                .orElseThrow(ApplicationException::new);
    }
}
