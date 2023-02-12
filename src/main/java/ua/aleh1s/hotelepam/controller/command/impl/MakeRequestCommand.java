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
import java.util.Optional;

import static ua.aleh1s.hotelepam.Utils.getIntValue;

public class MakeRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        Long applicationId = (Long) session.getAttribute("applicationId");
        Integer roomNumber = getIntValue(request, "roomNumber");

        RoomRepository roomRepository = AppContext.getInstance().getRoomRepository();
        Optional<RoomEntity> roomOptional = roomRepository.getByRoomNumber(roomNumber);

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.page.request");
        if (roomOptional.isEmpty()) {
            errorMessage = "There is no room with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        Optional<ApplicationEntity> applicationOptional = applicationRepository.getById(applicationId);

        if (applicationOptional.isEmpty()) {
            //todo: handle
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

        Long managerId = (Long) session.getAttribute("id");
        RequestEntity requestEntity = RequestEntity.Builder.newBuilder()
                .roomNumber(roomNumber)
                .managerId(managerId)
                .customerId(application.getCustomerId())
                .status(RequestStatus.NEW)
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
