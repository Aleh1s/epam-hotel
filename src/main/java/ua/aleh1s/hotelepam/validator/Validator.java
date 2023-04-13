package ua.aleh1s.hotelepam.validator;

import lombok.Getter;

import java.util.*;

import static java.util.Objects.*;

@Getter
public abstract class Validator<T> {

    protected static final String REQUIRED_VALUE_MESSAGE = "%s is required, but not present";
    protected static final String EMPTY_VALUE_MESSAGE = "%s should not be empty";

    protected Map<String, List<String>> messagesByRejectedValue;

    {
        this.messagesByRejectedValue = new HashMap<>();
    }

    public abstract void validate(T target);
    public boolean hasErrors() {
        return !messagesByRejectedValue.isEmpty();
    }

    protected void rejectValue(String field, String message) {
        List<String> messages = messagesByRejectedValue.get(field);

        if (nonNull(messages))
            messages.add(message);
        else
            messagesByRejectedValue.put(field, new LinkedList<>(List.of(message)));
    }

    protected boolean isBlank(String value) {
        return value.isBlank();
    }

    protected boolean isNull(String value) {
        return Objects.isNull(value);
    }

    protected String requiredValueMessage(String fieldName) {
        return String.format(REQUIRED_VALUE_MESSAGE, fieldName);
    }

    protected String emptyValueMessage(String fieldName) {
        return String.format(EMPTY_VALUE_MESSAGE, fieldName);
    }
}
