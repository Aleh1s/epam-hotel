package ua.aleh1s.hotelepam.mapper.dtomapper;

import lombok.Getter;
import ua.aleh1s.hotelepam.validator.ErrorContainer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
public abstract class Mapper<T, R> extends ErrorContainer {

    public abstract R map(T target);

    protected Integer parseInt(String fieldName, String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            rejectValue(fieldName, fieldName + " should be an integer");
        }
        return 0;
    }

    protected LocalDate parseLocalDate(String fieldName, String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            rejectValue(fieldName, fieldName + " is invalid");
        }
        return LocalDate.MIN;
    }

    protected BigDecimal parseBigDecimal(String fieldName, String value) {
        if (value.contains(","))
            value = value.replace(",", ".");

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            rejectValue(fieldName, fieldName + " should be a real number separated with '.' or ','");
        }
        return BigDecimal.ZERO;
    }
}
