package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.ReservationTokenService;
import ua.aleh1s.hotelepam.service.RoomService;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.*;

public class ConfirmBookingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReservationTokenService reservationTokenService = AppContext.getInstance().getReservationTokenService();
        ReservationService reservationService = AppContext.getInstance().getReservationService();
        RoomService roomService = AppContext.getInstance().getRoomService();

        String tokenId = request.getParameter("tokenId");

        ReservationTokenEntity token = reservationTokenService.getById(tokenId);
        ReservationEntity reservation = reservationService.getById(token.getReservationId());
        RoomEntity room = roomService.getByRoomNumber(reservation.getRoomNumber());

        if (room.getIsUnavailable())
            throw new ApplicationException("Room is unavailable now. Try to pick another room.",
                    ResourcesManager.getInstance().getValue("path.command.profile"));

        LocalDateTime tokenExpiredAt = token.getExpiredAt();
        if (tokenExpiredAt.isBefore(LocalDateTime.now())) {
            reservationService.cancelReservation(reservation);
            throw new ApplicationException("Token has already expired!");
        }

        reservationTokenService.confirmToken(token);

        reservation.setExpiredAt(LocalDateTime.now().plusDays(2));
        reservation.setStatus(PENDING_PAYMENT);
        reservationService.update(reservation);

        String path = ResourcesManager.getInstance().getValue("path.page.booking.confirmation");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return path;
    }
}
