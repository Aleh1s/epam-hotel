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
import ua.aleh1s.hotelepam.service.PaymentService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.IOException;

import static ua.aleh1s.hotelepam.utils.Utils.toDate;

public class PayReservationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        PaymentService paymentService = AppContext.getInstance().getPaymentService();

        HttpSession session = request.getSession(false);
        Long reservationId = Utils.getLongValue(request, "reservationId");
        Long userId = (Long) session.getAttribute("id");

        ReservationEntity reservation;
        try {
            reservation = paymentService.payReservation(reservationId, userId);
        } catch (ServiceException e) {
            e.setPath(resourcesManager.getValue("path.command.my.bookings"));
            throw e;
        }

        session.setAttribute("paymentPayedAt", toDate(reservation.getPayedAt()));
        session.setAttribute("paymentTotalAmount", reservation.getTotalAmount());

        return redirect(resourcesManager.getValue("path.page.success.payment"), response);
    }
}
