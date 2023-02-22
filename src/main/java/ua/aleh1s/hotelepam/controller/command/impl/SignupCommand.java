package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.service.RegistrationService;
import ua.aleh1s.hotelepam.service.UserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

public class SignupCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RegistrationService registrationService = AppContext.getInstance().getRegistrationService();

        // todo: write validation
        String phoneNumber = request.getParameter("phoneNumber").trim();
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();

        Locale defaultLocale = new Locale("");
        UserRole userRole = UserRole.CUSTOMER;

        UserEntity userEntity = UserEntity.Builder.newBuilder()
                .email(email).firstName(firstName).lastName(lastName)
                .phoneNumber(phoneNumber).password(password).locale(defaultLocale)
                .role(userRole).account(BigDecimal.ZERO)
                .build();

        registrationService.register(userEntity);

        String path = ResourcesManager.getInstance().getValue("path.page.login");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }
        return path;
    }
}
