package ua.aleh1s.hotelepam.database.querybuilder.node;

import ua.aleh1s.hotelepam.database.querybuilder.Operator;
import ua.aleh1s.hotelepam.database.querybuilder.entityconfiguration.Column;

public final class RangeNode<T> extends PredicateNode {

    private final Column column;
    private final T leftBound;
    private final T rightBound;

    private RangeNode(Operator operator, Column column, T leftBound, T rightBound) {
        super(operator);
        this.column = column;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public static <T> RangeNode<T> of(Operator operator, Column column, T leftBound, T rightBound) {
        return new RangeNode<>(operator, column, leftBound, rightBound);
    }

    public Column getColumn() {
        return column;
    }

    public T getLeftBound() {
        return leftBound;
    }

    public T getRightBound() {
        return rightBound;
    }
}
