package ua.aleh1s.hotelepam.validator;

import java.time.LocalDate;

public class DateIntervalValidator {

    public boolean isValid(LocalDate from, LocalDate to) {
        return from.isBefore(LocalDate.now()) || to.isBefore(from) || to.isEqual(from);
    }

}
