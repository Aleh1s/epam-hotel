package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.dto.BookInfoDto;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;

import java.io.IOException;
import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.PENDING;

public class ConfirmBookingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Long customerId = (Long) session.getAttribute("id");

        BookInfoDto bookingInfo = (BookInfoDto) session.getAttribute("bookInfo");
        session.removeAttribute("bookInfo");

        LocalDateTime now = LocalDateTime.now();
        ReservationEntity reservation = ReservationEntity.Builder.newBuilder()
                .roomNumber(bookingInfo.getRoomNumber())
                .customerId(customerId)
                .entryDate(bookingInfo.getEntryDate())
                .leavingDate(bookingInfo.getLeavingDate())
                .createdAt(now)
                .expiredAt(now.plusDays(2))
                .totalAmount(bookingInfo.getTotalAmount())
                .status(PENDING)
                .build();

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        reservationRepository.create(reservation);

        session.setAttribute("reservationTotalAmount", bookingInfo.getTotalAmount());
        session.setAttribute("reservationEntryDate", bookingInfo.getEntryDate());
        session.setAttribute("reservationLeavingDate", bookingInfo.getLeavingDate());

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
