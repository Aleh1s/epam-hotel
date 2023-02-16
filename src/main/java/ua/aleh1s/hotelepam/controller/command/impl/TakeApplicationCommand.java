package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.Utils;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.ApplicationEntity;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.repository.ApplicationRepository;

import java.util.Optional;

public class TakeApplicationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long applicationId = Utils.getLongValue(request, "applicationId");

        ApplicationRepository applicationRepository = AppContext.getInstance().getApplicationRepository();
        Optional<ApplicationEntity> applicationOptional = applicationRepository.getById(applicationId);

        String path = ResourcesManager.getInstance().getValue("path.page.error");
        if (applicationOptional.isEmpty()) {
            //todo: handle
            return path;
        }

        ApplicationEntity application = applicationOptional.get();

        String errorMessage;
        if (application.getStatus().equals(ApplicationStatus.PROCESSED)) {
            errorMessage = "Application is already closed";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        request.getSession(false).setAttribute("applicationId", applicationId);
        return ResourcesManager.getInstance().getValue("path.page.request");
    }
}
