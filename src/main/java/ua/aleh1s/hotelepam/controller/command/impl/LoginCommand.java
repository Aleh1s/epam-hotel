package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.controller.Page;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;
import ua.aleh1s.hotelepam.controller.dto.UserEntityDto;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        String errorMessage;
        String path = Page.LOGIN.getPath();

        if (userEntityOptional.isEmpty()) {
            errorMessage = String.format("User with email %s doesn't exist", email);
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(path, false);
        }

        UserEntity userEntity = userEntityOptional.get();
        boolean isPasswordValid = BCrypt.checkpw(password, userEntity.getPassword());
        if (!isPasswordValid) {
            errorMessage = "Password or email is incorrect";
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(path, false);
        }

        UserEntityDto dto = UserEntityDto.Builder.newBuilder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .timezone(userEntity.getTimezone())
                .locale(userEntity.getLocale())
                .role(userEntity.getRole())
                .build();

        HttpSession session = request.getSession();
        session.setAttribute("userDto", dto);

        boolean isRedirect;
        try {
            response.sendRedirect("/roomList.jsp"); //todo: redirect to valid path
            isRedirect = true;
        } catch (IOException e) {
            path = Page.ERROR_PAGE.getPath();
            isRedirect = false;
        }
        return Result.of(path, isRedirect);
    }
}
