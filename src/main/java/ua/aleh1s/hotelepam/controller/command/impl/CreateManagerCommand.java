package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.service.RegistrationService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

public class CreateManagerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        RegistrationService registrationService = AppContext.getInstance().getRegistrationService();

        // todo: write validation
        String phoneNumber = request.getParameter("phoneNumber").trim();
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();

        Locale defaultLocale = new Locale("");
        UserRole userRole = UserRole.MANAGER;

        UserEntity userEntity = UserEntity.builder()
                .email(email).firstName(firstName).lastName(lastName)
                .phoneNumber(phoneNumber).password(password).locale(defaultLocale)
                .role(userRole).account(BigDecimal.ZERO)
                .build();

        registrationService.register(userEntity);

        String path = resourcesManager.getValue("path.command.get.rooms");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }
        return path;
    }
}
