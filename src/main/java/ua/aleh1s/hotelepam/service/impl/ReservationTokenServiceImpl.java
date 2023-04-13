package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationTokenRepository;
import ua.aleh1s.hotelepam.service.ReservationTokenService;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class ReservationTokenServiceImpl implements ReservationTokenService {

    private final ReservationTokenRepository reservationTokenRepository;

    @Override
    public void create(ReservationTokenEntity reservationToken) {
        reservationTokenRepository.create(reservationToken);
    }

    @Override
    public void confirmToken(ReservationTokenEntity reservationToken) throws ServiceException {
        if (nonNull(reservationToken.getConfirmedAt()))
            throw new ServiceException("Token has already confirmed!");

        reservationToken.setConfirmedAt(LocalDateTime.now());
        reservationTokenRepository.update(reservationToken);
    }

    @Override
    public ReservationTokenEntity getById(String tokenId) throws ServiceException {
        return reservationTokenRepository.findById(tokenId)
                .orElseThrow(() -> new ServiceException("Reservation token with id " + tokenId + " does not exist"));
    }
}
