package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;

public class UnknownCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(404);
        return ResourcesManager.getInstance().getValue("path.page.error");
    }
}
