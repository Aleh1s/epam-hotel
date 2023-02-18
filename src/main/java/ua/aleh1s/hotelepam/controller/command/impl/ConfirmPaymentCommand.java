package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ConfirmPaymentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        Long reservationId = (Long) session.getAttribute("reservationId");
        Long userId = (Long) session.getAttribute("id");

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        Optional<ReservationEntity> reservationOptional = reservationRepository.getById(reservationId);

        String errorMessage, path = "/controller?command=paymentPage&reservationId=" + reservationId;
        if (reservationOptional.isEmpty()) {
            errorMessage = "There is no reservation with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        ReservationEntity reservation = reservationOptional.get();
        if (!reservation.getStatus().equals(ReservationStatus.CONFIRMED)) {
            errorMessage = "You cannot pay this reservation";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        if (reservation.getExpiredAt().isBefore(LocalDateTime.now())) {
            errorMessage = "Reservation is expired";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        path = ResourcesManager.getInstance().getValue("path.command.profile");
        if (userOptional.isEmpty()) {
            errorMessage = "There is no user with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        UserEntity user = userOptional.get();
        BigDecimal userAccount = user.getAccount();
        BigDecimal totalAmount = reservation.getTotalAmount();

        if (totalAmount.compareTo(userAccount) > 0) {
            errorMessage = "You don't have enough money";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        LocalDateTime payedAt = LocalDateTime.now();
        user.setAccount(userAccount.subtract(totalAmount));
        reservation.setStatus(ReservationStatus.PAYED);
        reservation.setPayedAt(payedAt);

        userRepository.update(user);
        reservationRepository.update(reservation);

        session.setAttribute("paymentPayedAt", payedAt);
        session.setAttribute("paymentTotalAmount", totalAmount);

        path = ResourcesManager.getInstance().getValue("path.page.success.payment");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}
