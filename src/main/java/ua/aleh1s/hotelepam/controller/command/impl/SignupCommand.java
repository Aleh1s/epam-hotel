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
import java.util.Objects;
import java.util.Optional;

public class SignupCommand implements Command {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        UserRepository userRepository = AppContext.getInstance().getUserRepository();

        String errorMessage = null;
        String page = Page.SIGNUP.getPath();

        Optional<UserEntity> userEntityByEmail = userRepository.findByEmail(email);
        if (userEntityByEmail.isPresent())
            errorMessage = String.format("User with email %s already exists", email);

        Optional<UserEntity> userEntityByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userEntityByPhoneNumber.isPresent())
            errorMessage = String.format("User with phone number %s already exists", phoneNumber);

        if (Objects.nonNull(errorMessage)) {
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(page, false);
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
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
            response.sendRedirect("/test.jsp"); //todo: redirect to valid page
            isRedirect = true;
        } catch (IOException e) {
            page = Page.ERROR_PAGE.getPath();
            isRedirect = false;
        }
        return Result.of(page, isRedirect);
    }
}
