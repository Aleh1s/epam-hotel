package ua.aleh1s.hotelepam.controller.exception.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.controller.exception.Handler;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;

public class ServiceExceptionHandler implements Handler {
    @Override
    public String handle(ApplicationException e, HttpServletRequest request, HttpServletResponse response) {
         ServiceException ex = (ServiceException) e;

         request.setAttribute("errorMessage", ex.getMessage());

         return ex.getPath();
    }
}
