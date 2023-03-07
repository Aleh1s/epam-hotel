package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.PaymentService;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.*;

@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ReservationService reservationService;
    private final UserService userService;

    @Override
    public ReservationEntity payReservation(Long reservationId, Long userId) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        ReservationEntity reservation = reservationService.getById(reservationId);

        String path = resourcesManager.getValue("path.command.my.bookings");
        if (!Objects.equals(reservation.getCustomerId(), userId))
            throw new ApplicationException("You cannot pay this reservation!", path);

        if (reservation.getExpiredAt().isBefore(LocalDateTime.now())) {
            reservationService.cancelReservation(reservation);
            throw new ApplicationException("Reservation was canceled!", path);
        }

        UserEntity user = userService.getById(userId);

        BigDecimal userAccount = user.getAccount();
        BigDecimal totalAmount = reservation.getTotalAmount();

        if (totalAmount.compareTo(userAccount) > 0)
            throw new ApplicationException("You don't have enough money!", path);

        user.setAccount(userAccount.subtract(totalAmount));
        reservation.setPayedAt(LocalDateTime.now());
        reservation.setStatus(PAYED);

        reservationService.update(reservation);
        userService.update(user);

        return reservation;
    }
}
