package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.service.BookingService;
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.time.LocalDate;

import static ua.aleh1s.hotelepam.utils.Utils.getLocalDateValue;

public class BookCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BookingService bookingService = AppContext.getInstance().getBookingService();

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");
        Integer roomNumber = (Integer) session.getAttribute("roomNumber");

        LocalDate checkIn = getLocalDateValue(request, "checkIn");
        LocalDate checkOut = getLocalDateValue(request, "checkOut");
        Period requestedPeriod = Period.range(checkIn, checkOut);

        ReservationEntity reservation = bookingService.bookRoom(roomNumber, userId, requestedPeriod);

        session.setAttribute("reservationTotalAmount", reservation.getTotalAmount());
        session.setAttribute("reservationCheckIn", checkIn);
        session.setAttribute("reservationCheckOut", checkOut);

        String path = ResourcesManager.getInstance().getValue("path.page.success.booking");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return path;
    }
}