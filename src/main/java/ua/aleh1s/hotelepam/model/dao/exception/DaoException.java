package ua.aleh1s.hotelepam.model.dao.exception;

import ua.aleh1s.hotelepam.exception.ApplicationException;

public class DaoException extends ApplicationException {
    public DaoException() {

    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
