package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.io.IOException;
import java.util.Optional;

import static ua.aleh1s.hotelepam.Utils.getIntValue;
import static ua.aleh1s.hotelepam.Utils.getLongValue;

public class ChangeReservationStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long reservationId = getLongValue(request, "reservationId");
        Integer reservationStatusIndex = getIntValue(request, "reservationStatus");
        ReservationStatus reservationStatus = ReservationStatus.atIndex(reservationStatusIndex);

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        Optional<ReservationEntity> reservationOptional = reservationRepository.getById(reservationId);

        String errorMessage, path = "/controller?command=reservationList";
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
