package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.controller.command.Result;

import java.io.IOException;

import static ua.aleh1s.hotelepam.controller.Page.ERROR_PAGE;

public class RedirectToErrorPage implements Command {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        String path = ERROR_PAGE.getPath();
        Result result;
        try {
            response.sendRedirect(path);
            result = Result.of(path, true);
        } catch (IOException e) {
            result = Result.of(path, false);
        }
        return result;
    }
}
