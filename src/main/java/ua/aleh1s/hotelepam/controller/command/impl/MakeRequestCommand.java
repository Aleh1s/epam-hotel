package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.*;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.utils.Utils.getIntValue;
import static ua.aleh1s.hotelepam.utils.Utils.getLocalDateValue;

public class MakeRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        RoomService roomService = AppContext.getInstance().getRoomService();
        ApplicationService applicationService = AppContext.getInstance().getApplicationService();
        RequestService requestService = AppContext.getInstance().getRequestService();

        Integer roomNumber = getIntValue(request, "roomNumber");
        LocalDate checkIn = getLocalDateValue(request, "checkIn");
        LocalDate checkOut = getLocalDateValue(request, "checkOut");

        HttpSession session = request.getSession(false);
        Long applicationId = (Long) session.getAttribute("applicationId");

        ApplicationEntity application = applicationService.getApplicationById(applicationId);

        String path = resourcesManager.getValue("path.command.get.applications");
        if (application.getStatus().equals(ApplicationStatus.PROCESSED))
            throw new ApplicationException("Application is already processed", path);

        Period requestedPeriod = Period.between(checkIn, checkOut);
        if (!roomService.isRoomAvailable(roomNumber, requestedPeriod))
            throw new ApplicationException(String.format("Room with number %s is unavailable", roomNumber), path);

        application.setStatus(ApplicationStatus.PROCESSED);
        applicationService.update(application);

        BigDecimal totalAmount = roomService.getTotalPrice(roomNumber, requestedPeriod);

        RequestEntity requestEntity = RequestEntity.builder()
                .roomNumber(roomNumber)
                .customerId(application.getCustomerId())
                .status(RequestStatus.NEW)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .totalAmount(totalAmount)
                .createdAt(LocalDateTime.now())
                .build();
        requestService.create(requestEntity);

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }
        return path;
    }
}
