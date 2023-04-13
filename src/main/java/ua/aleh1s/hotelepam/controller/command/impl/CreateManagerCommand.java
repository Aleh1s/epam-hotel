package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.dto.SignupCredentials;
import ua.aleh1s.hotelepam.model.dtomapper.requesttodto.SignupCredentialsDtoMapper;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.service.AuthService;

import java.io.IOException;

public class CreateManagerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        AuthService authService = AppContext.getInstance().getAuthService();
        SignupCredentialsDtoMapper credentialsDtoMapper = AppContext.getInstance().getSignupCredentialsDtoMapper();

        SignupCredentials credentials = credentialsDtoMapper.apply(request);

        String path = resourcesManager.getValue("path.page.create.manager");
        try {
            authService.register(credentials, UserRole.MANAGER);
        } catch (ApplicationException e) {
            e.setPath(path);
            throw e;
        }

        return redirect(resourcesManager.getValue("path.command.get.rooms"), response);
    }
}
