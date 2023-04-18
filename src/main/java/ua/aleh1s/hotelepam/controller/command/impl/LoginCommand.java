package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.model.dto.LoginCredentials;
import ua.aleh1s.hotelepam.model.dto.UserDto;
import ua.aleh1s.hotelepam.mapper.dtomapper.requesttodto.LoginCredentialsDtoMapper;
import ua.aleh1s.hotelepam.service.AuthService;

import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        AuthService authService = AppContext.getInstance().getAuthService();

        HttpSession session = request.getSession();

        LoginCredentialsDtoMapper mapper = new LoginCredentialsDtoMapper();
        LoginCredentials credentials = mapper.apply(request);

        String path;
        try {
            UserDto user = authService.login(credentials);

            session.setAttribute("id", user.id());
            session.setAttribute("lang", user.locale().getLanguage());
            session.setAttribute("role", user.role());

            switch (user.role()) {
                case CUSTOMER -> path = ResourcesManager.getInstance().getValue("path.page.home");
                case MANAGER -> path = ResourcesManager.getInstance().getValue("path.command.get.applications");
                case ADMIN -> path = ResourcesManager.getInstance().getValue("path.command.get.rooms");
                default -> throw new IllegalArgumentException("Unknown user role");
            }
        } catch (ApplicationException e) {
            e.setPath(ResourcesManager.getInstance().getValue("path.page.login"));
            throw e;
        }

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }

        return path;
    }
}
