package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.controller.Page;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;

import java.io.IOException;
import java.time.LocalDate;

public class ApplicationCommand implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();

        String numberOfGuestsStr = request.getParameter("numberOfGuests");
        String apartmentClassStr = request.getParameter("apartmentClass");
        String dateOfEntryStr = request.getParameter("dateOfEntry");
        String dateOfLeavingStr = request.getParameter("dateOfLeaving");

        Integer numberOfGuests = Integer.parseInt(numberOfGuestsStr);
        LocalDate dateOfEntry = LocalDate.parse(dateOfEntryStr);
        LocalDate dateOfLeaving = LocalDate.parse(dateOfLeavingStr);
        RoomClass roomClass = RoomClass.valueOf(apartmentClassStr);

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");

        ApplicationEntity applicationEntry = ApplicationEntity.Builder.newBuilder()
                .guestsNumber(numberOfGuests).apartmentClass(roomClass)
                .entryDate(dateOfEntry).leavingDate(dateOfLeaving)
                .status(ApplicationStatus.NEW).customerId(id)
                .build();

        applicationRepository.create(applicationEntry);

        String path = Page.APPLICATION.getPath();
        boolean isRedirect;
        try {
            response.sendRedirect(path);
            isRedirect = true;
        } catch (IOException e) {
            path = Page.ERROR_PAGE.getPath();
            isRedirect = false;
        }

        return Result.of(path, isRedirect);
    }
}
