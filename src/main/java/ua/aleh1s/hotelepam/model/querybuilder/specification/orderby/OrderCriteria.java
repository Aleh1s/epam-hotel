package ua.aleh1s.hotelepam.model.querybuilder.specification.orderby;

import ua.aleh1s.hotelepam.model.querybuilder.specification.Column;

public class OrderCriteria {

    private final Column column;
    private final OrderDirection direction;

    private OrderCriteria(Column column, OrderDirection direction) {
        this.column = column;
        this.direction = direction;
    }

    public static OrderCriteria of(Column column, OrderDirection direction) {
        return new OrderCriteria(column, direction);
    }

    public Column getColumn() {
        return column;
    }

    public OrderDirection getDirection() {
        return direction;
    }
}
