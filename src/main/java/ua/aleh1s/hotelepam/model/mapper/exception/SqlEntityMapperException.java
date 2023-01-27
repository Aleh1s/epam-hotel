package ua.aleh1s.hotelepam.model.mapper.exception;

public class SqlEntityMapperException extends Exception {
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
