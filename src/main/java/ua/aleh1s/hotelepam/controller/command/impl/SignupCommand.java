package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.controller.Page;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;
import ua.aleh1s.hotelepam.controller.dto.UserEntityDto;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.entity.UserRole;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;

public class SignupCommand implements Command {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        UserRepository userRepository = AppContext.getInstance().getUserRepository();

        String page = Page.SIGNUP.getPath();
        
        if (userRepository.findByEmail(email).isPresent()) {
            String errorMessage = String.format("User with email %s already exists", email);
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(page, false);
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String zoneOffsetStr = request.getParameter("timezoneOffset");
        ZoneId timezone = ZoneId.ofOffset("UTC", ZoneOffset.of(zoneOffsetStr));
        Locale defaultLocale = Locale.ENGLISH;
        UserRole userRole = UserRole.CUSTOMER;

        UserEntity user = UserEntity.Builder.newBuilder()
                .email(email).firstName(firstName).lastName(lastName)
                .phoneNumber(phoneNumber).password(password).timezone(timezone)
                .locale(defaultLocale).role(userRole).build();

        userRepository.create(user);

        UserEntityDto userEntityDto = UserEntityDto.Builder.newBuilder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .timezone(timezone)
                .phoneNumber(phoneNumber)
                .locale(defaultLocale)
                .role(userRole)
                .build();

        HttpSession session = request.getSession();
        session.setAttribute("userDto", userEntityDto);

        boolean isRedirect;
        try {
            response.sendRedirect("/hello.jsp");
            isRedirect = true;
        } catch (IOException e) {
            page = Page.ERROR_PAGE.name();
            isRedirect = false;
        }

        return Result.of(page, isRedirect);
    }
}
