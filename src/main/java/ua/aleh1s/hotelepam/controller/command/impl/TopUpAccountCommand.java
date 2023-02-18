package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import static ua.aleh1s.hotelepam.appcontext.Utils.getBigDecimalValue;

public class TopUpAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BigDecimal amount = getBigDecimalValue(request, "amount");
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        UserRepository userRepository = AppContext.getInstance().getUserRepository();
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        String errorMessage, path = ResourcesManager.getInstance().getValue("path.command.profile");
        if (userOptional.isEmpty()) {
            errorMessage = "There is no user with such id";
            request.setAttribute("errorMessage", errorMessage);
            return path;
        }

        UserEntity user = userOptional.get();
        user.setAccount(user.getAccount().add(amount));
        userRepository.update(user);

        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}
