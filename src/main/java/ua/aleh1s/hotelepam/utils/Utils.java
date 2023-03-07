package ua.aleh1s.hotelepam.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Objects.*;

public class Utils {

    private Utils() {throw new RuntimeException("Cannot instantiate Utils.class");}

    public static Integer getIntValue(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    public static Long getLongValue(HttpServletRequest request, String name) {
        return Long.parseLong(request.getParameter(name));
    }

    public static LocalDate getLocalDateValue(HttpServletRequest request, String name) {
        return LocalDate.parse(request.getParameter(name));
    }

    public static BigDecimal getBigDecimalValue(HttpServletRequest request, String name) {
        return BigDecimal.valueOf(Double.parseDouble(request.getParameter(name)));
    }

    public static Integer getIntValueOrDefault(HttpServletRequest request, String name, Integer def) {
        String intParameterStr = request.getParameter(name);
        if (nonNull(intParameterStr) && !intParameterStr.isBlank())
            return Integer.parseInt(intParameterStr);
        return def;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime != null ? Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public static java.sql.Date toDate(LocalDate ld) {
        return ld != null ? java.sql.Date.valueOf(ld) : null;
    }

    public static int getNumberOfPages(long totalCount, int pageSize) {
        return (int) Math.ceil(totalCount / (double) pageSize);
    }

    public static boolean isReservationPeriodValid(Period period) {
        LocalDate checkIn = period.start();
        LocalDate checkOut = period.end();

        LocalDate now = LocalDate.now();
        boolean isCheckInValid = checkIn.isAfter(now) || checkIn.isEqual(now),
                isDateRangeValid = checkIn.isBefore(checkOut);

        return isCheckInValid && isDateRangeValid;
    }

    public static Timestamp toTimestamp(LocalDateTime ldt) {
        return ldt != null ? Timestamp.valueOf(ldt) : null;
    }
}
