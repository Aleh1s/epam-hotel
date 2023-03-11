package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.UserService;

import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        UserService userService = AppContext.getInstance().getUserService();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserEntity user;
        String errorMessage = "Password or email is incorrect",
                path = resourcesManager.getValue("path.page.login");

        try {
            user = userService.getByEmail(email);
        } catch (ApplicationException e) {
            throw new ApplicationException(errorMessage, path);
        }

        boolean isPasswordValid = BCrypt.checkpw(password, user.getPassword());
        if (!isPasswordValid)
            throw new ApplicationException(errorMessage, path);

        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("lang", user.getLocale().getLanguage());
        session.setAttribute("role", user.getRole());

        switch (user.getRole()) {
            case CUSTOMER -> path = ResourcesManager.getInstance().getValue("path.page.home");
            case MANAGER -> path = ResourcesManager.getInstance().getValue("path.command.get.applications");
            case ADMIN -> path = ResourcesManager.getInstance().getValue("path.command.get.rooms");
            default -> throw new IllegalArgumentException("Unknown user role");
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
