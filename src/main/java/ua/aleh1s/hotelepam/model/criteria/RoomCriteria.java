package ua.aleh1s.hotelepam.model.criteria;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.ApplicationException;
import ua.aleh1s.hotelepam.utils.Period;
import ua.aleh1s.hotelepam.utils.Utils;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
public class RoomCriteria {
    private Period period;

    private Order price;
    private Order guests;
    private Order clazz;

    public void setSort(String sort) {
        dropSort();

        String[] split = sort.split(",");

        String field = split[0];
        String order = split[1];

        switch (field) {
            case "price" -> {
                if (order.equals("asc")) {
                    price = Order.ASC;
                } else {
                    price = Order.DESC;
                }
            }
            case "guests" -> {
                if (order.equals("asc")) {
                    guests = Order.ASC;
                } else {
                    guests = Order.DESC;
                }
            }
            case "class" -> {
                if (order.equals("asc")) {
                    clazz = Order.ASC;
                } else {
                    clazz = Order.DESC;
                }
            }
            default -> throw new IllegalArgumentException("Unknown field");
        }
    }



    public void dropSort() {
        this.price = null;
        this.guests = null;
        this.clazz = null;
    }
}
