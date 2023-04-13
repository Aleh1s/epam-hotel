package ua.aleh1s.hotelepam.service.exception;

import ua.aleh1s.hotelepam.exception.ServiceException;

public class PhoneAlreadyExistsException extends ServiceException {
    public PhoneAlreadyExistsException() {
    }

    public PhoneAlreadyExistsException(String message) {
        super(message);
    }

    public PhoneAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
