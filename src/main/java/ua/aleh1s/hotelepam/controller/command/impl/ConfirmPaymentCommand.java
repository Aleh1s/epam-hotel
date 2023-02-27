package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.service.PaymentService;
import ua.aleh1s.hotelepam.service.impl.PaymentServiceImpl;

import java.io.IOException;

public class ConfirmPaymentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        PaymentService paymentService = AppContext.getInstance().getPaymentService();

        HttpSession session = request.getSession(false);
        Long reservationId = (Long) session.getAttribute("reservationId");
        Long userId = (Long) session.getAttribute("id");

        ReservationEntity reservation
                = paymentService.payReservation(reservationId, userId);

        session.setAttribute("paymentPayedAt", reservation.getPayedAt());
        session.setAttribute("paymentTotalAmount", reservation.getTotalAmount());

        String path = resourcesManager.getValue("path.page.success.payment");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }

        return path;
    }
}
