package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.UserService;
import ua.aleh1s.hotelepam.service.impl.UserServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;

import static ua.aleh1s.hotelepam.utils.Utils.getBigDecimalValue;

public class TopUpAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();

        BigDecimal amount = getBigDecimalValue(request, "amount");

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");

        UserService userService = AppContext.getInstance().getUserService();
        UserEntity user = userService.getById(userId);

        user.setAccount(user.getAccount().add(amount));
        userService.update(user);

        String path = resourcesManager.getValue("path.command.profile");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = resourcesManager.getValue("path.page.error");
        }

        return path;
    }
}
