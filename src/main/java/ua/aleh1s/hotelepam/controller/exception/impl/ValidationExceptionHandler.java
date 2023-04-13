package ua.aleh1s.hotelepam.controller.exception.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.controller.exception.Handler;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.service.exception.ValidationException;

public class ValidationExceptionHandler implements Handler {
    @Override
    public String handle(ApplicationException e, HttpServletRequest request, HttpServletResponse response) {
        ValidationException ex = (ValidationException) e;

        request.setAttribute("errors", ex.getMessagesByRejectedValue());

        return ex.getPath();
    }
}
