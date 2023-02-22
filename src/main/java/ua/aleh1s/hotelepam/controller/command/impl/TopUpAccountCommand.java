package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.model.repository.UserRepository;
import ua.aleh1s.hotelepam.service.UserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import static ua.aleh1s.hotelepam.utils.Utils.getBigDecimalValue;

public class TopUpAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BigDecimal amount = getBigDecimalValue(request, "amount");

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        UserService userService = AppContext.getInstance().getUserService();
        UserEntity user = userService.getById(userId);

        user.setAccount(user.getAccount().add(amount));
        userService.update(user);

        String path = ResourcesManager.getInstance().getValue("path.page.profile");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }

        return path;
    }
}
