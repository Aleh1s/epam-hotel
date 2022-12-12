package ua.aleh1s.hotelepam.database.exception;

import ua.aleh1s.hotelepam.exception.ApplicationException;

public class JdbcException extends ApplicationException {
    public JdbcException() {
    }

    public JdbcException(String message) {
        super(message);
    }

    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcException(Throwable cause) {
        super(cause);
    }
}
