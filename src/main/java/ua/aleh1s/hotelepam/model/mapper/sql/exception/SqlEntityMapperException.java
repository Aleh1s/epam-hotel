package ua.aleh1s.hotelepam.model.mapper.sql.exception;

import ua.aleh1s.hotelepam.exception.ApplicationException;

public class SqlEntityMapperException extends ApplicationException {
    public SqlEntityMapperException() {
    }

    public SqlEntityMapperException(String message) {
        super(message);
    }

    public SqlEntityMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlEntityMapperException(Throwable cause) {
        super(cause);
    }
}
