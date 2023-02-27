package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationTokenEntity;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.ReservationTokenService;
import ua.aleh1s.hotelepam.service.impl.ReservationServiceImpl;
import ua.aleh1s.hotelepam.service.impl.ReservationTokenServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.*;

public class ConfirmBookingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReservationTokenService reservationTokenService = AppContext.getInstance().getReservationTokenService();
        ReservationService reservationService = AppContext.getInstance().getReservationService();

        String tokenId = request.getParameter("tokenId");

        ReservationTokenEntity token = reservationTokenService.getById(tokenId);

        LocalDateTime tokenExpiredAt = token.getExpiredAt();
        if (tokenExpiredAt.isBefore(LocalDateTime.now()))
            throw new ApplicationException("Token has already expired!");

        if (nonNull(token.getConfirmedAt()))
            throw new ApplicationException("Token has already confirmed!");

        ReservationEntity reservation = reservationService.getById(token.getReservationId());

        reservationTokenService.confirmToken(token);
        reservationService.changeStatus(reservation, PENDING_PAYMENT);

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
