package ua.aleh1s.hotelepam.controller.exception.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.controller.exception.Handler;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;

public class ApplicationExceptionHandler implements Handler {
    @Override
    public String handle(ApplicationException e, HttpServletRequest request, HttpServletResponse response) {
         request.setAttribute("errorMessage", e.getMessage());
         return e.getPath();
    }
}
