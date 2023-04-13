package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.model.dto.SignupCredentials;
import ua.aleh1s.hotelepam.model.dtomapper.requesttodto.SignupCredentialsDtoMapper;
import ua.aleh1s.hotelepam.service.AuthService;

import java.io.IOException;

import static ua.aleh1s.hotelepam.model.entity.UserRole.*;

public class SignupCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        AuthService authService = AppContext.getInstance().getAuthService();
        SignupCredentialsDtoMapper credentialsDtoMapper = AppContext.getInstance().getSignupCredentialsDtoMapper();

        SignupCredentials credentials = credentialsDtoMapper.apply(request);

        try {
            authService.register(credentials, CUSTOMER);
        } catch (ApplicationException e) {
            e.setPath(resourcesManager.getValue("path.page.signup"));
            throw e;
        }

        String path = resourcesManager.getValue("path.page.login");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }
        return path;
    }
}
