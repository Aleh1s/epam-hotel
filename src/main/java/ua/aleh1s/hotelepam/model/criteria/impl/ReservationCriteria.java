package ua.aleh1s.hotelepam.model.criteria.impl;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.criteria.Criteria;
import ua.aleh1s.hotelepam.model.entity.ReservationStatus;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReservationCriteria implements Criteria {

    private final Map<ReservationStatus, Boolean> statusMap;

    private ReservationCriteria(Map<ReservationStatus, Boolean> statusMap) {
        this.statusMap = statusMap;
    }

    public static ReservationCriteria valueOf(HttpServletRequest request) {
        Map<ReservationStatus, Boolean> collect = Arrays.stream(ReservationStatus.values())
                .collect(Collectors.toMap(Function.identity(),
                        status -> Objects.nonNull(request.getParameter(status.name().toLowerCase()))));

        return new ReservationCriteria(collect);
    }

    @Override
    public String build() {
        boolean isNothingOn = statusMap.values().stream().noneMatch(value -> value);

        if (isNothingOn)
            return "";
        StringJoiner statusJoiner = new StringJoiner(",", "(", ")");
        for (ReservationStatus status : ReservationStatus.values()) {
            boolean isOn = statusMap.get(status);
            if (isOn) {
                statusJoiner.add(String.valueOf(status.getIndex()));
            }
        }
        return "where status in " + statusJoiner;
    }

    public Map<String, Boolean> getParams() {
        return statusMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name().toLowerCase(), Map.Entry::getValue));
    }
}
