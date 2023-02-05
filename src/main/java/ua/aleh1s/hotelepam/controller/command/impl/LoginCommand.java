package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import ua.aleh1s.hotelepam.AppContext;
import ua.aleh1s.hotelepam.controller.Page;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;
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

        String errorMessage = "Password or email is incorrect";
        String path = Page.LOGIN.getPath();

        if (userEntityOptional.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(path, false);
        }

        UserEntity userEntity = userEntityOptional.get();
        boolean isPasswordValid = BCrypt.checkpw(password, userEntity.getPassword());
        if (!isPasswordValid) {
            request.setAttribute("errorMessage", errorMessage);
            return Result.of(path, false);
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", userEntity.getId());
        session.setAttribute("timezone", userEntity.getTimezone().getId());
        session.setAttribute("lang", userEntity.getLocale().getLanguage());
        session.setAttribute("role", userEntity.getRole().name());

        boolean isRedirect;
        try {
            response.sendRedirect("/roomList.jsp");
            isRedirect = true;
        } catch (IOException e) {
            path = Page.ERROR_PAGE.getPath();
            isRedirect = false;
        }
        return Result.of(path, isRedirect);
    }
}
