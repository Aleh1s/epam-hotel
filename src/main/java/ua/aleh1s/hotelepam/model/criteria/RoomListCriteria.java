package ua.aleh1s.hotelepam.model.criteria;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.constant.RoomListParam;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomStatus;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static ua.aleh1s.hotelepam.Utils.getIntValueOrDefault;
import static ua.aleh1s.hotelepam.model.constant.RoomListParam.*;

public class RoomListCriteria implements Criteria {

    private String criteria;
    private final Map<String, Integer> intParams;
    private final Map<String, Boolean> boolParams;
    private static final Map<String, Integer> INT_DEFAULT_VALUES;

    static {
        INT_DEFAULT_VALUES = new HashMap<>();
        INT_DEFAULT_VALUES.put(PRICE_FROM.getValue(), 20);
        INT_DEFAULT_VALUES.put(PRICE_TO.getValue(), 500);
        INT_DEFAULT_VALUES.put(PERSONS_FROM.getValue(), 1);
        INT_DEFAULT_VALUES.put(PERSONS_TO.getValue(), 10);
    }

    private RoomListCriteria(Map<String, Integer> intParams, Map<String, Boolean> boolParams) {
        this.intParams = intParams;
        this.boolParams = boolParams;
    }

    public static RoomListCriteria valueOf(HttpServletRequest request) {
        EnumSet<RoomListParam> intParamSet = EnumSet.range(PRICE_FROM, PERSONS_TO);
        EnumSet<RoomListParam> boolParamSet = EnumSet.range(STANDARD, UNAVAILABLE);

        Map<String, Integer> intParams = intParamSet.stream().map(RoomListParam::getValue)
                .collect(Collectors.toMap(Function.identity(), str ->
                        getIntValueOrDefault(request, str, INT_DEFAULT_VALUES.get(str))));

        Map<String, Boolean> boolParams = boolParamSet.stream().map(RoomListParam::getValue)
                .collect(Collectors.toMap(Function.identity(), str ->
                        nonNull(request.getParameter(str))));

        return new RoomListCriteria(intParams, boolParams);
    }

    @Override
    public String build() {
        if (nonNull(criteria))
            return criteria;

        StringBuilder criteria = new StringBuilder("where (price between ")
                .append(intParams.get(PRICE_FROM.getValue())).append(" and ")
                .append(intParams.get(PRICE_TO.getValue())).append(") and (persons_number between ")
                .append(intParams.get(PERSONS_FROM.getValue())).append(" and ")
                .append(intParams.get(PERSONS_TO.getValue())).append(") ");

        StringJoiner classJoiner = new StringJoiner(",", "(", ")");
        for (RoomClass roomClass : RoomClass.values()) {
            boolean isOn = boolParams.get(roomClass.name().toLowerCase());
            if (isOn) {
                classJoiner.add(String.valueOf(roomClass.getIndex()));
            }
        }
        criteria.append("and (class in ").append(classJoiner).append(") ");

        StringJoiner statusJoiner = new StringJoiner(",", "(", ")");
        for (RoomStatus roomStatus : RoomStatus.values()) {
            boolean isOn = boolParams.get(roomStatus.name().toLowerCase());
            if (isOn) {
                statusJoiner.add(String.valueOf(roomStatus.getIndex()));
            }
        }
        criteria.append("and (status in ").append(statusJoiner).append(")");

        this.criteria = criteria.toString();
        return this.criteria;
    }

    public Map<String, Integer> getIntParams() {
        return intParams;
    }

    public Map<String, Boolean> getBoolParams() {
        return boolParams;
    }
}
