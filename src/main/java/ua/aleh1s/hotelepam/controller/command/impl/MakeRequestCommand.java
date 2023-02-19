package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
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

import static ua.aleh1s.hotelepam.utils.Utils.getIntValue;
import static ua.aleh1s.hotelepam.utils.Utils.getLocalDateValue;

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

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        Optional<ApplicationEntity> applicationOptional = applicationRepository.getById(applicationId);

        if (applicationOptional.isEmpty()) {
            //todo:
            return path;
        }

        ApplicationEntity application = applicationOptional.get();
        if (application.getStatus().equals(ApplicationStatus.PROCESSED)) {
            errorMessage = "Application is already processed";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        application.setStatus(ApplicationStatus.PROCESSED);
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

        try {
            response.sendRedirect(ResourcesManager.getInstance().getValue("path.command.application.list"));
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }
        return path;
    }
}
