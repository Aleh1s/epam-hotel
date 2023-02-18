package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.appcontext.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;

import java.io.IOException;
import java.time.LocalDate;

public class ApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();

        Integer guestsNumber = Utils.getIntValue(request, "guestsNumber");
        Integer roomClassIdx = Utils.getIntValue(request, "roomClass");
        LocalDate entryDate = Utils.getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = Utils.getLocalDateValue(request, "leavingDate");
        RoomClass roomClass = RoomClass.atIndex(roomClassIdx);

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.page.application");
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

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");

        ApplicationEntity applicationEntry = ApplicationEntity.Builder.newBuilder()
                .guestsNumber(guestsNumber).roomClass(roomClass)
                .entryDate(entryDate).leavingDate(leavingDate)
                .status(ApplicationStatus.NEW).customerId(id)
                .build();

        applicationRepository.create(applicationEntry);

        session.setAttribute("guestsNumber", guestsNumber);
        session.setAttribute("roomClass", roomClass);
        session.setAttribute("entryDate", entryDate);
        session.setAttribute("leavingDate", leavingDate);

        try {
            response.sendRedirect(ResourcesManager.getInstance().getValue("path.page.success.application"));
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}
