package ua.aleh1s.hotelepam.database.querybuilder;

public class QueryBuilderException extends RuntimeException {

    public QueryBuilderException() {
    }

    public QueryBuilderException(String message) {
        super(message);
    }

    public QueryBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryBuilderException(Throwable cause) {
        super(cause);
    }
}
