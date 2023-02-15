package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

public class SignupCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        Locale defaultLocale = new Locale("");
        UserRole userRole = UserRole.CUSTOMER;

        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        String errorMessage, path = ResourcesManager.getInstance().getValue("path.page.signup");

        Optional<UserEntity> userEntityByEmail = userRepository.findByEmail(email);
        if (userEntityByEmail.isPresent()) {
            errorMessage = String.format("User with email %s already exists", email);
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        Optional<UserEntity> userEntityByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userEntityByPhoneNumber.isPresent()) {
            errorMessage = String.format("User with phone number %s already exists", phoneNumber);
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        UserEntity userEntity = UserEntity.Builder.newBuilder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .password(hashedPassword)
                .locale(defaultLocale)
                .role(userRole)
                .account(BigDecimal.ZERO)
                .build();

        userRepository.create(userEntity);

        path = ResourcesManager.getInstance().getValue("path.page.login");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }
        return path;
    }
}
