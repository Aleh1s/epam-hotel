package ua.aleh1s.hotelepam.appcontext;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static java.util.Objects.*;

public class Utils {

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

    public static Integer getIntContextParamValue(HttpServletRequest request, String key) {
        String initParameter = request.getServletContext().getInitParameter(key);
        return Integer.parseInt(initParameter);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime != null ? Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public static int getNumberOfPages(int totalCount, int pageSize) {
        return (int) Math.ceil(totalCount / (double) pageSize);
    }
}
