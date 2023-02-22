package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;

public class ApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        ApplicationService applicationService = AppContext.getInstance().getApplicationService();

        Integer guestsNumber = Utils.getIntValue(request, "guestsNumber");
        Integer roomClassIdx = Utils.getIntValue(request, "roomClass");
        LocalDate entryDate = Utils.getLocalDateValue(request, "entryDate");
        LocalDate leavingDate = Utils.getLocalDateValue(request, "leavingDate");
        RoomClass roomClass = RoomClass.atIndex(roomClassIdx);

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");

        String path = resourcesManager.getValue("path.page.application");
        if (entryDate.isBefore(LocalDate.now()))
            throw new ApplicationException("Entry date cannot be before now", path);

        if (leavingDate.isBefore(entryDate) || leavingDate.isEqual(entryDate))
            throw new ApplicationException("Entry date must be before leaving date", path);

        ApplicationEntity applicationEntry = ApplicationEntity.Builder.newBuilder()
                .guestsNumber(guestsNumber).roomClass(roomClass)
                .entryDate(entryDate).leavingDate(leavingDate)
                .status(ApplicationStatus.NEW).customerId(id)
                .build();

        applicationService.create(applicationEntry);

        session.setAttribute("guestsNumber", guestsNumber);
        session.setAttribute("roomClass", roomClass);
        session.setAttribute("entryDate", entryDate);
        session.setAttribute("leavingDate", leavingDate);

        try {
            response.sendRedirect(resourcesManager.getValue("path.page.success.application"));
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }

        return path;
    }
}
