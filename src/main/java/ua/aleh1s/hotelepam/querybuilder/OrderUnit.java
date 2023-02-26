package ua.aleh1s.hotelepam.querybuilder;

import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.Column;

public class OrderUnit {

    private final Column column;
    private final Direction direction;

    private OrderUnit(
            Column column,
            Direction direction) {
        this.column = column;
        this.direction = direction;
    }

    public static OrderUnit by(Column column, Direction direction) {
        return new OrderUnit(column, direction);
    }

    @Override
    public String toString() {
        return String.format("%s %s", column.getName(), direction.toString());
    }

    public enum Direction {
        ASC,
        DESC;

        @Override
        public String toString() {
            return name();
        }
    }
}
