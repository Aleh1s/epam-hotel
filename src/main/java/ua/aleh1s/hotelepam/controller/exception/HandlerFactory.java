package ua.aleh1s.hotelepam.controller.exception;

import ua.aleh1s.hotelepam.controller.exception.impl.ServiceExceptionHandler;
import ua.aleh1s.hotelepam.controller.exception.impl.ValidationExceptionHandler;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.service.exception.ValidationException;

public class HandlerFactory {

    private static HandlerFactory instance;
    private final ValidationExceptionHandler validationExceptionHandler;
    private final ServiceExceptionHandler serviceExceptionHandler;

    {
        this.validationExceptionHandler = new ValidationExceptionHandler();
        this.serviceExceptionHandler = new ServiceExceptionHandler();
    }

    public static synchronized HandlerFactory getInstance() {
        if (instance == null)
            instance = new HandlerFactory();

        return instance;
    }

    public Handler getHandler(ApplicationException e) {
        if (e instanceof ValidationException)
            return validationExceptionHandler;
        else if (e instanceof ServiceException)
            return serviceExceptionHandler;

        throw new IllegalArgumentException("There is no handler for such exception " + e);
    }
}
