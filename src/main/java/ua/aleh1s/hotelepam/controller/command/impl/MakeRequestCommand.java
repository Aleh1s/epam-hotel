package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.*;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static ua.aleh1s.hotelepam.Utils.getIntValue;
import static ua.aleh1s.hotelepam.Utils.getLocalDateValue;
import static ua.aleh1s.hotelepam.model.entity.RoomStatus.FREE;

public class MakeRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        Long applicationId = (Long) session.getAttribute("applicationId");
        Integer roomNumber = getIntValue(request, "roomNumber");
        LocalDate entryDate = getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = getLocalDateValue(request, "leavingDate");

        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        Optional<RoomEntity> roomOptional = roomRepository.getByRoomNumber(roomNumber);

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.page.request");
        if (roomOptional.isEmpty()) {
            errorMessage = "There is no room with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        RoomEntity room = roomOptional.get();
        if (!room.getStatus().equals(FREE)) {
            errorMessage = "Room is already taken";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        Optional<ApplicationEntity> applicationOptional = applicationRepository.getById(applicationId);

        if (applicationOptional.isEmpty()) {
            errorMessage = "There is no application with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        ApplicationEntity application = applicationOptional.get();
        if (application.getStatus().equals(ApplicationStatus.CLOSED)) {
            errorMessage = "Application is already closed";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        application.setStatus(ApplicationStatus.CLOSED);
        applicationRepository.update(application);

        Long daysBetween = ChronoUnit.DAYS.between(entryDate, leavingDate);
        BigDecimal totalAmount = room.getPrice().multiply(BigDecimal.valueOf(daysBetween));

        RequestEntity requestEntity = RequestEntity.Builder.newBuilder()
                .roomNumber(roomNumber)
                .customerId(application.getCustomerId())
                .status(RequestStatus.NEW)
                .entryDate(entryDate)
                .leavingDate(leavingDate)
                .totalAmount(totalAmount)
                .build();

        RequestRepository requestRepository = AppContext.getInstance().getRequestRepository();
        requestRepository.create(requestEntity);

        path = ResourcesManager.getInstance().getValue("path.page.success.request");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }
        return path;
    }
}
