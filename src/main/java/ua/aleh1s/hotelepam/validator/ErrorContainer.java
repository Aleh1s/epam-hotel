package ua.aleh1s.hotelepam.validator;

import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Getter
public abstract class ErrorContainer {

    private final Map<String, List<String>> messagesByRejectedValue;

    {
        this.messagesByRejectedValue = new HashMap<>();
    }

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

}
