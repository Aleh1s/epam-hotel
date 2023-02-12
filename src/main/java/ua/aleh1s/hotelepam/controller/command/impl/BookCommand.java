package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.RoomStatus;
import ua.aleh1s.hotelepam.model.repository.ReservationRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static ua.aleh1s.hotelepam.Utils.getLocalDateValue;

public class BookCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Integer roomNumber = (Integer) session.getAttribute("roomNumber");
        LocalDate entryDate = getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = getLocalDateValue(request, "leavingDate");

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.page.book");
        if (entryDate.isBefore(LocalDate.now())) {
            errorMessage = "Entry date cannot be before now";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        if (leavingDate.isBefore(entryDate) || leavingDate.isEqual(entryDate)) {
            errorMessage = "Entry date must be before leaving date";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        Optional<RoomEntity> roomEntityOptional = roomRepository.getByRoomNumber(roomNumber);

        if (roomEntityOptional.isEmpty()) {
            //todo: handle
            return ResourcesManager.getInstance().getValue("path.page.error");
        }

        RoomEntity roomEntity = roomEntityOptional.get();
        if (!roomEntity.getStatus().equals(RoomStatus.FREE)) {
            errorMessage = "Room isn't free";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }
        roomEntity.setStatus(RoomStatus.BOOKED);
        roomRepository.update(roomEntity);

        Long customerId = (Long) session.getAttribute("id");
        Long daysBetween = ChronoUnit.DAYS.between(entryDate, leavingDate);
        BigDecimal pricePerNight = roomEntity.getPrice();
        BigDecimal totalAmount = pricePerNight.multiply(BigDecimal.valueOf(daysBetween));

        LocalDateTime now = LocalDateTime.now();
        ReservationEntity reservation = ReservationEntity.Builder.newBuilder()
                .roomNumber(roomNumber)
                .customerId(customerId)
                .entryDate(entryDate)
                .leavingDate(leavingDate)
                .createdAt(now)
                .expiredAt(now.plusDays(2))
                .totalAmount(totalAmount)
                .build();

        ReservationRepository reservationRepository = AppContext.getInstance().getReservationRepository();
        reservationRepository.create(reservation);

        path = ResourcesManager.getInstance().getValue("path.page.success.booking");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}