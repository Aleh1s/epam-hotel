package ua.aleh1s.hotelepam;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Objects.*;

public class Utils {

    public static String getValueOrDefault(HttpServletRequest request, String name, String def) {
        return Optional.ofNullable(request.getParameter(name)).orElse(def);
    }

    public static Integer getIntValue(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    public static Long getLongValue(HttpServletRequest request, String name) {
        return Long.parseLong(request.getParameter(name));
    }

    public static LocalDate getLocalDateValue(HttpServletRequest request, String name) {
        return LocalDate.parse(request.getParameter(name));
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

}
