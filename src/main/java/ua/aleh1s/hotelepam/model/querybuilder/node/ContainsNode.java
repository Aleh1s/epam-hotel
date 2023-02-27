package ua.aleh1s.hotelepam.model.querybuilder.node;

import ua.aleh1s.hotelepam.model.querybuilder.Operator;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;

public final class ContainsNode<T> extends PredicateNode {

    private final Column comparedColumn;
    private final T[] comparedWith;

    private ContainsNode(Operator operator, Column comparedColumn, T[] comparedWith) {
        super(operator);
        this.comparedColumn = comparedColumn;
        this.comparedWith = comparedWith;
    }

    public static <T> ContainsNode<T> of(Operator operator, Column comparedColumn, T[] comparedWith) {
        return new ContainsNode<>(operator, comparedColumn, comparedWith);
    }

    public Column getComparedColumn() {
        return comparedColumn;
    }

    public T[] getComparedWith() {
        return comparedWith;
    }
}
