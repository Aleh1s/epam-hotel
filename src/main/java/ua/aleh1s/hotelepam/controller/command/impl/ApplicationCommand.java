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
import ua.aleh1s.hotelepam.utils.Period;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.utils.Utils.*;

public class ApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        ApplicationService applicationService = AppContext.getInstance().getApplicationService();

        Integer guests = getIntValue(request, "guests");
        Integer classIndex = getIntValue(request, "clazz");
        LocalDate checkIn = getLocalDateValue(request, "checkIn");
        LocalDate checkOut = getLocalDateValue(request, "checkOut");

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");

        String path = resourcesManager.getValue("path.page.application");
        if (!isReservationPeriodValid(Period.between(checkIn, checkOut)))
            throw new ApplicationException("Date range is invalid", path);

        RoomClass clazz = RoomClass.atIndex(classIndex);
        ApplicationEntity applicationEntry = ApplicationEntity.builder()
                .guests(guests)
                .clazz(clazz)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .status(ApplicationStatus.NEW)
                .customerId(id)
                .createdAt(LocalDateTime.now())
                .build();
        applicationService.create(applicationEntry);

        session.setAttribute("guests", guests);
        session.setAttribute("clazz", clazz);
        session.setAttribute("checkIn", toDate(checkIn));
        session.setAttribute("checkOut", toDate(checkOut));

        path = resourcesManager.getValue("path.page.success.application");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            throw new ApplicationException();
        }

        return path;
    }
}
