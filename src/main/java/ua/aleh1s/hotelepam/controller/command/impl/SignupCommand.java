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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class SignupCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        UserRepository userRepository = AppContext.getInstance().getUserRepository();

        String errorMessage = null;
        String path = ResourcesManager.getInstance().getValue("path.page.signup");

        Optional<UserEntity> userEntityByEmail = userRepository.findByEmail(email);
        if (userEntityByEmail.isPresent())
            errorMessage = String.format("User with email %s already exists", email);

        Optional<UserEntity> userEntityByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userEntityByPhoneNumber.isPresent())
            errorMessage = String.format("User with phone number %s already exists", phoneNumber);

        if (Objects.nonNull(errorMessage)) {
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String zoneOffsetStr = request.getParameter("timezoneOffset");
        ZoneId timezone = ZoneId.ofOffset("UTC", ZoneOffset.of(zoneOffsetStr));
        Locale defaultLocale = new Locale("");
        UserRole userRole = UserRole.CUSTOMER;

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        UserEntity userEntity = UserEntity.Builder.newBuilder()
                .email(email).firstName(firstName).lastName(lastName)
                .phoneNumber(phoneNumber).password(hashedPassword).timezone(timezone)
                .locale(defaultLocale).role(userRole)
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
