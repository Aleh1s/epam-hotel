package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.service.BookingService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.util.Objects;

import static ua.aleh1s.hotelepam.model.entity.RequestStatus.CONFIRMED;
import static ua.aleh1s.hotelepam.utils.Utils.getLongValue;
import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class ConfirmRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        RequestService requestService = AppContext.getInstance().getRequestService();
        BookingService bookingService = AppContext.getInstance().getBookingService();

        Long requestId = getLongValue(request, "requestId");
        HttpSession session = request.getSession(false);

        ReservationEntity reservation;
        try {
            requestService.confirmRequest(requestId);
            reservation = bookingService.bookRoom(requestId);
        } catch (ServiceException e) {
            e.setPath(ResourcesManager.getInstance().getValue("path.command.profile"));
            throw e;
        }

        session.setAttribute("reservationTotalAmount", reservation.getTotalAmount());
        session.setAttribute("reservationCheckIn", reservation.getCheckIn());
        session.setAttribute("reservationCheckOut", reservation.getCheckOut());

        return redirect(resourcesManager.getValue("path.page.success.booking"), response);
    }
}
