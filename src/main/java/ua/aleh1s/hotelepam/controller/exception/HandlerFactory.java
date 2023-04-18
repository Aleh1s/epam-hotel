package ua.aleh1s.hotelepam.controller.exception;

import ua.aleh1s.hotelepam.controller.exception.impl.ApplicationExceptionHandler;
import ua.aleh1s.hotelepam.controller.exception.impl.ValidationExceptionHandler;
import ua.aleh1s.hotelepam.exception.ApplicationException;
import ua.aleh1s.hotelepam.service.exception.ValidationException;

public class HandlerFactory {

    private static HandlerFactory instance;
    private final ValidationExceptionHandler validationExceptionHandler;
    private final ApplicationExceptionHandler applicationExceptionHandler;

    {
        this.validationExceptionHandler = new ValidationExceptionHandler();
        this.applicationExceptionHandler = new ApplicationExceptionHandler();
    }

    public static synchronized HandlerFactory getInstance() {
        if (instance == null)
            instance = new HandlerFactory();

        return instance;
    }

    public Handler getHandler(ApplicationException e) {
        if (e instanceof ValidationException)
            return validationExceptionHandler;
        else
            return applicationExceptionHandler;
    }
}
