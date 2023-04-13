package ua.aleh1s.hotelepam.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ControllerException;

import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException;

    default String redirect(String path, HttpServletResponse response) {
        try {
            response.sendRedirect(path);
            path = "redirect";
        } catch (IOException e) {
            path = ResourcesManager.getInstance().getValue("path.page.error");
        }
        return path;
    }
}
