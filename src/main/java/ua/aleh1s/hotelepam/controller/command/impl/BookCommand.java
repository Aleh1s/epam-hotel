package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.service.BookingService;
import ua.aleh1s.hotelepam.utils.Period;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;

public class BookCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        BookingService bookingService = AppContext.getInstance().getBookingService();

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("id");
        Integer roomNumber = (Integer) session.getAttribute("roomNumber");
        LocalDate checkIn = (LocalDate) session.getAttribute("requestedCheckIn");
        LocalDate checkOut = (LocalDate) session.getAttribute("requestedCheckOut");

        Period requestedPeriod = Period.between(checkIn, checkOut);

        ReservationEntity reservation;
        try {
            reservation = bookingService.bookRoom(roomNumber, userId, requestedPeriod);
        } catch (ServiceException e) {
            e.setPath(resourcesManager.getValue("path.command.view.room"));
            throw e;
        }

        session.setAttribute("reservationTotalAmount", reservation.getTotalAmount());
        session.setAttribute("reservationCheckIn", Utils.toDate(checkIn));
        session.setAttribute("reservationCheckOut", Utils.toDate(checkOut));

        return redirect(resourcesManager.getValue("path.page.success.booking"), response);
    }
}