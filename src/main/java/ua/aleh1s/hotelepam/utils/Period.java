package ua.aleh1s.hotelepam.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Period(LocalDate start, LocalDate end) {

    public static Period between(LocalDate start, LocalDate end) {
        return new Period(start, end);
    }

    public boolean intersects(Period another) {
        boolean thisIsBeforeAnother = end.isBefore(another.start) || end.isEqual(another.start);
        boolean thisIsAfterAnother = start.isAfter(another.end) || start.isEqual(another.end);
        return !(thisIsBeforeAnother || thisIsAfterAnother);
    }

    public int getDaysBetween() {
        return (int) ChronoUnit.DAYS.between(start, end);
    }
}
