package ua.aleh1s.hotelepam.model.criteria;

import jakarta.servlet.http.HttpServletRequest;
import ua.aleh1s.hotelepam.model.entity.RoomClass;
import ua.aleh1s.hotelepam.model.entity.RoomStatus;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class RoomListCriteria implements Criteria {

    private String criteria;
    private HttpServletRequest request;

    public RoomListCriteria(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String build() {
        if (Objects.nonNull(criteria))
            return criteria;

        String priceFromStr = Optional.ofNullable(request.getParameter("priceFrom")).orElse("20");
        String priceToStr = Optional.ofNullable(request.getParameter("priceTo")).orElse("500");
        String personsNumberFromStr = Optional.ofNullable(request.getParameter("personsFrom")).orElse("1");
        String personsNumberToStr = Optional.ofNullable(request.getParameter("personsTo")).orElse("10");

        Integer priceFrom = Integer.parseInt(priceFromStr);
        Integer priceTo = Integer.parseInt(priceToStr);
        Integer personsNumberFrom = Integer.parseInt(personsNumberFromStr);
        Integer personsNumberTo = Integer.parseInt(personsNumberToStr);

        boolean isStandard = Optional.ofNullable(request.getParameter("standard")).isPresent();
        boolean isSuperior = Optional.ofNullable(request.getParameter("superior")).isPresent();
        boolean isFamily = Optional.ofNullable(request.getParameter("family")).isPresent();
        boolean isBusiness = Optional.ofNullable(request.getParameter("business")).isPresent();
        boolean isPresident = Optional.ofNullable(request.getParameter("president")).isPresent();

        boolean isFree = Optional.ofNullable(request.getParameter("free")).isPresent();
        boolean isBooked = Optional.ofNullable(request.getParameter("booked")).isPresent();
        boolean isBusy = Optional.ofNullable(request.getParameter("busy")).isPresent();
        boolean isUnavailable = Optional.ofNullable(request.getParameter("unavailable")).isPresent();

        StringBuffer criteria = new StringBuffer();

        criteria.append("where (price between ").append(priceFrom).append(" and ").append(priceTo).append(") ");
        criteria.append("and (persons_number between ").append(personsNumberFrom).append(" and ").append(personsNumberTo).append(") ");

        StringJoiner classJoiner = new StringJoiner(",", "(", ")");
        if (!isStandard && !isSuperior && !isFamily && !isBusiness && !isPresident) {
            for (RoomClass roomClass: RoomClass.values()){
                classJoiner.add(String.valueOf(roomClass.getIndex()));
            }
        } else {
            if (isStandard) classJoiner.add(String.valueOf(RoomClass.STANDARD.getIndex()));
            if (isSuperior) classJoiner.add(String.valueOf(RoomClass.SUPERIOR.getIndex()));
            if (isFamily) classJoiner.add(String.valueOf(RoomClass.FAMILY.getIndex()));
            if (isBusiness) classJoiner.add(String.valueOf(RoomClass.BUSINESS.getIndex()));
            if (isPresident) classJoiner.add(String.valueOf(RoomClass.PRESIDENT.getIndex()));
        }

        criteria.append("and (class in ").append(classJoiner).append(") ");

        StringJoiner statusJoiner = new StringJoiner(",", "(", ")");
        if (!isFree && !isBooked && !isBusy && !isUnavailable) {
            for (RoomStatus roomStatus: RoomStatus.values()) {
                statusJoiner.add(String.valueOf(roomStatus.getIndex()));
            }
        } else {
            if (isFree) statusJoiner.add(String.valueOf(RoomStatus.FREE.getIndex()));
            if (isBooked) statusJoiner.add(String.valueOf(RoomStatus.BOOKED.getIndex()));
            if (isBusy) statusJoiner.add(String.valueOf(RoomStatus.BUSY.getIndex()));
            if (isUnavailable) statusJoiner.add(String.valueOf(RoomStatus.UNAVAILABLE.getIndex()));
        }

        criteria.append("and (status in ").append(statusJoiner).append(");");

        this.criteria = criteria.toString();
        return this.criteria;
    }
}
