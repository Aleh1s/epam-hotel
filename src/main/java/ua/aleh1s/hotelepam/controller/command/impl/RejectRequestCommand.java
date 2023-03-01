package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.RequestEntity;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;
import ua.aleh1s.hotelepam.service.RequestService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.IOException;
import java.util.Objects;

public class RejectRequestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        RequestService requestService = AppContext.getInstance().getRequestService();

        Long requestId = Utils.getLongValue(request, "requestId");

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        RequestEntity requestEntity = requestService.getById(requestId);

        String path = resourcesManager.getValue("path.command.profile");
        if (!Objects.equals(requestEntity.getCustomerId(), userId))
            throw new ApplicationException("You cannot change status", path);

        requestService.changeStatus(requestEntity, RequestStatus.REJECTED);

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }

        return path;
    }
}
