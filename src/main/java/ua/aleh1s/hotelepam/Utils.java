package ua.aleh1s.hotelepam;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class Utils {

    public static String getValueOrDefault(HttpServletRequest request, String name, String def) {
        return Optional.ofNullable(request.getParameter(name)).orElse(def);
    }

    public static Integer getIntValueOrDefault(HttpServletRequest request, String name, Integer def) {
        return Optional.ofNullable(request.getParameter(name))
                .map(Integer::parseInt).orElse(def);
    }
}
