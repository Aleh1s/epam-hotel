package ua.aleh1s.hotelepam.exception;

import lombok.Getter;
import lombok.Setter;

public class ApplicationException extends Exception {

    @Setter
    @Getter
    protected String path;

    public ApplicationException() {
        super("Oops... something went wrong");
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, String path) {
        super(message);
        this.path = path;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
