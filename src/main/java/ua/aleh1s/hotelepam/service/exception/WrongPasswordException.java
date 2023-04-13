package ua.aleh1s.hotelepam.service.exception;

import ua.aleh1s.hotelepam.exception.ServiceException;

public class WrongPasswordException extends ServiceException {
    public WrongPasswordException() {
        super("Password is wrong");
    }

}
