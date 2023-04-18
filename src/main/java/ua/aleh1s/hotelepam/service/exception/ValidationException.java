package ua.aleh1s.hotelepam.service.exception;

import lombok.Getter;
import ua.aleh1s.hotelepam.exception.ServiceException;

import java.util.List;
import java.util.Map;

@Getter
public class ValidationException extends ServiceException {

    private final Map<String, List<String>> messagesByRejectedValue;

    public ValidationException(Map<String, List<String>> messagesByRejectedValue) {
        this.messagesByRejectedValue = messagesByRejectedValue;
    }

    public ValidationException(Map<String, List<String>> messagesByRejectedValue, String path) {
        setPath(path);
        this.messagesByRejectedValue = messagesByRejectedValue;
    }
}
