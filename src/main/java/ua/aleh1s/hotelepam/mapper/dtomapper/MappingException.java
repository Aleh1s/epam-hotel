package ua.aleh1s.hotelepam.mapper.dtomapper;

import lombok.Getter;
import ua.aleh1s.hotelepam.exception.ApplicationException;

import java.util.List;
import java.util.Map;

@Getter
public class MappingException extends ApplicationException {

    private Map<String, String> errorByFieldName;

    public MappingException(Map<String, String> errorByFieldName) {
        this.errorByFieldName = errorByFieldName;
    }

    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, String path) {
        super(message, path);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }
}
