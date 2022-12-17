package ua.aleh1s.hotelepam.transaction.exception;

import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;

public class TransactionException extends JdbcException {
    public TransactionException() {
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
}
