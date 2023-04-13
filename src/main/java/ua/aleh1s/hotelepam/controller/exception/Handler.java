package ua.aleh1s.hotelepam.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.exception.ApplicationException;

public interface Handler {
    String handle(ApplicationException e, HttpServletRequest request, HttpServletResponse response);
}
