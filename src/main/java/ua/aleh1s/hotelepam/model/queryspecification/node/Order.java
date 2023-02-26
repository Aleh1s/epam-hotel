package ua.aleh1s.hotelepam.model.queryspecification.node;

import ua.aleh1s.hotelepam.model.querybuilder.specification.Column;

public class Order {

    private final Column column;
    private final Direction direction;

    private Order(
            Column column,
            Direction direction) {
        this.column = column;
        this.direction = direction;
    }

    public static Order by(Column column, Direction direction) {
        return new Order(column, direction);
    }

    public Column getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
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
