package ua.aleh1s.hotelepam.service.exception;

import ua.aleh1s.hotelepam.exception.ServiceException;

public class EmailAlreadyExistsException extends ServiceException {

    public EmailAlreadyExistsException() {
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
