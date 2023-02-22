package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.*;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;
import ua.aleh1s.hotelepam.model.repository.RequestRepository;
import ua.aleh1s.hotelepam.model.repository.RoomRepository;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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
        LocalDate entryDate = getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = getLocalDateValue(request, "leavingDate");

        HttpSession session = request.getSession(false);
        Long applicationId = (Long) session.getAttribute("applicationId");

        ApplicationEntity application = applicationService.getById(applicationId);

        String path = resourcesManager.getValue("path.page.request");
        if (application.getStatus().equals(ApplicationStatus.PROCESSED))
            throw new ApplicationException("Application is already processed", path);

        application.setStatus(ApplicationStatus.PROCESSED);
        applicationService.update(application);

        BigDecimal totalAmount = roomService.getTotalPrice(roomNumber, Period.range(entryDate, leavingDate));

        RequestEntity requestEntity = RequestEntity.Builder.newBuilder()
                .roomNumber(roomNumber)
                .customerId(application.getCustomerId())
                .status(RequestStatus.NEW)
                .entryDate(entryDate)
                .leavingDate(leavingDate)
                .totalAmount(totalAmount)
                .build();

        requestService.create(requestEntity);

        try {
            response.sendRedirect(resourcesManager.getValue("path.command.application.list"));
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }
        return path;
    }
}
