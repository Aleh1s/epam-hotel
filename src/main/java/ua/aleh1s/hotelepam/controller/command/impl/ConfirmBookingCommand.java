package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.Utils.*;
import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.PENDING;

public class ConfirmBookingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long customerId = (Long) request.getSession(false).getAttribute("id");
        Integer roomNumber = getIntValue(request, "roomNumber");
        LocalDate entryDate = getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = getLocalDateValue(request, "leavingDate");
        BigDecimal totalAmount = getBigDecimalValue(request, "totalAmount");

        LocalDateTime now = LocalDateTime.now();
        ReservationEntity reservation = ReservationEntity.Builder.newBuilder()
                .roomNumber(roomNumber)
                .customerId(customerId)
                .entryDate(entryDate)
                .leavingDate(leavingDate)
                .createdAt(now)
                .expiredAt(now.plusDays(2))
                .totalAmount(totalAmount)
                .status(PENDING)
                .build();

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        reservationRepository.create(reservation);

        HttpSession session = request.getSession(false);
        session.setAttribute("reservationTotalAmount", totalAmount);
        session.setAttribute("reservationEntryDate", entryDate);
        session.setAttribute("reservationLeavingDate", leavingDate);

        String path = ResourcesManager.getInstance().getValue("path.page.success.booking");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}
