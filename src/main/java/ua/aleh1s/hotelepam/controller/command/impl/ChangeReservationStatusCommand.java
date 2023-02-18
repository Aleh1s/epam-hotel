package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.io.IOException;
import java.util.Optional;

import static ua.aleh1s.hotelepam.appcontext.Utils.getIntValue;
import static ua.aleh1s.hotelepam.appcontext.Utils.getLongValue;

public class ChangeReservationStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        UserRole userRole = (UserRole) session.getAttribute("role");

        Long reservationId = getLongValue(request, "reservationId");
        Integer reservationStatusIndex = getIntValue(request, "reservationStatus");
        ReservationStatus reservationStatus = ReservationStatus.atIndex(reservationStatusIndex);

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        Optional<ReservationEntity> reservationOptional = reservationRepository.getById(reservationId);

        String errorMessage;
        String path;

        if (userRole.equals(UserRole.CUSTOMER)) {
            path = ResourcesManager.getInstance().getValue("path.command.my.bookings");
        } else {
            path = ResourcesManager.getInstance().getValue("path.command.reservation.list");
        }

        if (reservationOptional.isEmpty()) {
            errorMessage = "There is no reservation with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        ReservationEntity reservation = reservationOptional.get();
        reservation.setStatus(reservationStatus);
        reservationRepository.update(reservation);

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}
