package ua.aleh1s.hotelepam.database.querybuilder.node;

import ua.aleh1s.hotelepam.database.querybuilder.Operator;
import ua.aleh1s.hotelepam.database.querybuilder.entityconfiguration.Column;

public class NullCheckNode extends PredicateNode {

    private final Column comparedColumn;

    protected NullCheckNode(Operator operator, Column comparedColumn) {
        super(operator);
        this.comparedColumn = comparedColumn;
    }

    public static NullCheckNode of(Operator operator, Column comparedColumn) {
        return new NullCheckNode(operator, comparedColumn);
    }

    public Column getComparedColumn() {
        return comparedColumn;
    }
}
