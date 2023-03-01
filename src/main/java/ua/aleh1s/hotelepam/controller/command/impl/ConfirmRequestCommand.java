package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.service.BookingService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.util.Objects;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.CONFIRMED;
import static ua.aleh1s.hotelepam.utils.Utils.getLongValue;

public class ConfirmRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        RequestService requestService = AppContext.getInstance().getRequestService();
        BookingService bookingService = AppContext.getInstance().getBookingService();

        Long requestId = getLongValue(request, "requestId");

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        RequestEntity requestEntity = requestService.getById(requestId);

        String path = resourcesManager.getValue("path.command.profile");
        if (!Objects.equals(requestEntity.getCustomerId(), userId))
            throw new ApplicationException("You cannot change status", path);

        requestService.changeStatus(requestEntity, CONFIRMED);

        ReservationEntity reservation = bookingService.bookRoom(
                requestEntity.getRoomNumber(),
                requestEntity.getCustomerId(),
                Period.range(
                        requestEntity.getCheckIn(),
                        requestEntity.getCheckOut()
                )
        );

        session.setAttribute("reservationTotalAmount", reservation.getTotalAmount());
        session.setAttribute("reservationCheckIn", requestEntity.getCheckIn());
        session.setAttribute("reservationCheckOut", requestEntity.getCheckOut());

        path = ResourcesManager.getInstance().getValue("path.page.success.booking");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return path;
    }
}
