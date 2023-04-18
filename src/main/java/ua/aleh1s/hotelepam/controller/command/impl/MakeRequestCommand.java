package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto.HttpRequestRequestDtoMapper;
import ua.aleh1s.hotelepam.model.dto.RequestDto;
import ua.aleh1s.hotelepam.model.entity.*;
import ua.aleh1s.hotelepam.service.ApplicationService;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.exception.ValidationException;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static ua.aleh1s.hotelepam.utils.Utils.getIntValue;
import static ua.aleh1s.hotelepam.utils.Utils.getLocalDateValue;

public class MakeRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        RequestService requestService = AppContext.getInstance().getRequestService();

        HttpSession session = request.getSession(false);
        Long applicationId = (Long) session.getAttribute("applicationId");

        HttpRequestRequestDtoMapper mapper = new HttpRequestRequestDtoMapper();
        RequestDto requestDto = mapper.map(request);

        String path = resourcesManager.getValue("path.command.view.application.details") + "&applicationId=" + applicationId;
        if (mapper.hasErrors())
            throw new ValidationException(mapper.getMessagesByRejectedValue(), path);

        try {
            requestService.create(requestDto, applicationId);
        } catch (ApplicationException e) {
            e.setPath(path);
            throw e;
        }

        return redirect(resourcesManager.getValue("path.command.get.applications"), response);
    }
}
