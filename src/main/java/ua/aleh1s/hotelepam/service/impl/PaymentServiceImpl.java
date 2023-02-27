package ua.aleh1s.hotelepam.service.impl;

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

public class PaymentServiceImpl implements PaymentService {

    private final ReservationService reservationService;
    private final UserService userService;

    public PaymentServiceImpl(
            ReservationService reservationService,
            UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @Override
    public ReservationEntity payReservation(Long reservationId, Long userId) {
        ReservationEntity reservation = reservationService.getById(reservationId);

        String path = "/controller?command=paymentPage&reservationId=" + reservationId;
        if (!Objects.equals(reservation.getCustomerId(), userId))
            throw new ApplicationException();

        if (!reservation.getStatus().equals(ReservationStatus.PENDING_PAYMENT))
            throw new ApplicationException("You cannot pay this reservation", path);

        if (reservation.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new ApplicationException("Reservation is expired", path);

        UserEntity user = userService.getById(userId);

        BigDecimal userAccount = user.getAccount();
        BigDecimal totalAmount = reservation.getTotalAmount();

        path = ResourcesManager.getInstance().getValue("path.command.profile");
        if (totalAmount.compareTo(userAccount) > 0)
            throw new ApplicationException("You don't have enough money", path);

        LocalDateTime payedAt = LocalDateTime.now();

        user.setAccount(userAccount.subtract(totalAmount));
        reservation.setStatus(ReservationStatus.PAYED);
        reservation.setPayedAt(payedAt);

        userService.update(user);
        reservationService.update(reservation);

        return reservation;
    }

}
