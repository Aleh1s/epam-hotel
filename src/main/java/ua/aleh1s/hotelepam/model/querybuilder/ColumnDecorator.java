package ua.aleh1s.hotelepam.model.querybuilder;

import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;
import ua.aleh1s.hotelepam.model.querybuilder.node.ComparisonNode;
import ua.aleh1s.hotelepam.model.querybuilder.node.ContainsNode;
import ua.aleh1s.hotelepam.model.querybuilder.node.RangeNode;
import ua.aleh1s.hotelepam.model.querybuilder.node.PredicateNode;

public class ColumnDecorator {

    private final Column column;

    private ColumnDecorator(Column column) {
        this.column = column;
    }

    public static ColumnDecorator decorate(Column column) {
        return new ColumnDecorator(column);
    }

    public PredicateNode equal(Object value) {
        return ComparisonNode.of(Operator.EQUAL, column, value);
    }

    public PredicateNode notEqual(Object value) {
        return ComparisonNode.of(Operator.NOT_EQUAL, column, value);
    }

    public PredicateNode gte(Object value) {
        return ComparisonNode.of(Operator.GREATER_THAN_OR_EQUAL, column, value);
    }

    public PredicateNode lt(Object value) {
        return ComparisonNode.of(Operator.LESS_THAN_OR_EQUAL, column, value);
    }

    public <T> PredicateNode in(T... params) {
        return ContainsNode.of(Operator.IN, column, params);
    }

    public <T> PredicateNode notIn(T... params) {
        return ContainsNode.of(Operator.NOT_IN, column, params);
    }

    public <T> PredicateNode between(T leftBound, T rightBound) {
        return RangeNode.of(Operator.BETWEEN, column, leftBound, rightBound);
    }

    public <T> PredicateNode notBetween(T leftBound, T rightBound) {
        return RangeNode.of(Operator.NOT_BETWEEN, column, leftBound, rightBound);
    }

    public PredicateNode like(String pattern) {
        return ComparisonNode.of(Operator.LIKE, column, pattern);
    }

    public PredicateNode notLike(String pattern) {
        return ComparisonNode.of(Operator.NOT_LIKE, column, pattern);
    }

    public InsertionValue set(Object value) {
        return InsertionValue.of(column, value);
    }

    public Column getColumn() {
        return column;
    }
}
