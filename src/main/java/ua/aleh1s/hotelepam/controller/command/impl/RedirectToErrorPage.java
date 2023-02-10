package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;

import java.io.IOException;

public class RedirectToErrorPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String path = ResourcesManager.getInstance().getValue("path.page.error");
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
