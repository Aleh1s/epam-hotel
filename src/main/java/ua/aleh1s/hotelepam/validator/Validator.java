package ua.aleh1s.hotelepam.validator;

import lombok.Getter;
import ua.aleh1s.hotelepam.utils.Period;
import ua.aleh1s.hotelepam.utils.Utils;

import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.*;
import static ua.aleh1s.hotelepam.utils.Utils.*;

@Getter
public abstract class Validator<T> extends ErrorContainer {

    protected static final String REQUIRED_VALUE_MESSAGE = "%s is required, but not present";
    protected static final String EMPTY_VALUE_MESSAGE = "%s should not be empty";

    public abstract void validate(T target);

    protected boolean isBlank(String value) {
        return value.isBlank();
    }

    protected boolean isNull(Object value) {
        return Objects.isNull(value);
    }

    protected String requiredValueMessage(String fieldName) {
        return String.format(REQUIRED_VALUE_MESSAGE, fieldName);
    }

    protected String emptyValueMessage(String fieldName) {
        return String.format(EMPTY_VALUE_MESSAGE, fieldName);
    }

    protected void validatePeriod(LocalDate checkIn, LocalDate checkOut) {
        String checkInFieldName = "checkIn", checkOutFieldName = "checkOut";

        var isNull = false;
        if (isNull(checkIn)) {
            rejectValue(checkInFieldName, requiredValueMessage(checkInFieldName));
            isNull = true;
        }

        if (isNull(checkOut)) {
            rejectValue(checkOutFieldName, requiredValueMessage(checkOutFieldName));
            isNull = true;
        }

        if (!isNull && !isReservationPeriodValid(Period.between(checkIn, checkOut)))
            rejectValue("period", "Period is invalid");
    }
}
