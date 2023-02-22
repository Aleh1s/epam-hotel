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
import ua.aleh1s.hotelepam.service.ReservationService;

import java.io.IOException;
import java.util.Optional;

import static ua.aleh1s.hotelepam.utils.Utils.getIntValue;
import static ua.aleh1s.hotelepam.utils.Utils.getLongValue;

public class ChangeReservationStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        ReservationService reservationService = AppContext.getInstance().getReservationService();

        HttpSession session = request.getSession(false);
        UserRole userRole = (UserRole) session.getAttribute("role");

        Long reservationId = getLongValue(request, "reservationId");
        Integer reservationStatusIndex = getIntValue(request, "reservationStatus");
        ReservationStatus reservationStatus = ReservationStatus.atIndex(reservationStatusIndex);

        ReservationEntity reservation = reservationService.getById(reservationId);

        String path;
        if (userRole.equals(UserRole.CUSTOMER)) {
            path = resourcesManager.getValue("path.command.my.bookings");
        } else {
            path = resourcesManager.getValue("path.command.reservation.list");
        }

        reservation.setStatus(reservationStatus);
        reservationService.update(reservation);

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }

        return path;
    }
}
