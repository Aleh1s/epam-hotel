package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.PaymentService;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.*;

@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ReservationService reservationService;
    private final UserService userService;
    private final RoomService roomService;

    @Override
    public ReservationEntity payReservation(Long reservationId, Long userId) throws ServiceException {
        ReservationEntity reservation = reservationService.getById(reservationId);
        RoomEntity room = roomService.getByRoomNumber(reservation.getRoomNumber());

        if (!Objects.equals(reservation.getCustomerId(), userId))
            throw new ServiceException("You cannot pay this reservation!");

        if (room.getIsUnavailable())
            throw new ServiceException("Room is unavailable now. Try to pick another room.");

        if (reservation.getExpiredAt().isBefore(LocalDateTime.now())) {
            reservationService.cancelReservation(reservation);
            throw new ServiceException("Reservation was canceled!");
        }

        UserEntity user = userService.getById(userId);

        BigDecimal userAccount = user.getAccount();
        BigDecimal totalAmount = reservation.getTotalAmount();

        if (totalAmount.compareTo(userAccount) > 0)
            throw new ServiceException("You don't have enough money!");

        user.setAccount(userAccount.subtract(totalAmount));
        reservation.setPayedAt(LocalDateTime.now());
        reservation.setStatus(PAYED);

        reservationService.update(reservation);
        userService.update(user);

        return reservation;
    }
}
