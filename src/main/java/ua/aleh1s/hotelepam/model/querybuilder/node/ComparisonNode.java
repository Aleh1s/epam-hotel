package ua.aleh1s.hotelepam.model.querybuilder.node;

import ua.aleh1s.hotelepam.model.querybuilder.Operator;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;

public final class ComparisonNode extends PredicateNode {
    
    private final Column comparedColumn;
    private final Object comparedWith;
    
    private ComparisonNode(Operator operator, Column comparedColumn, Object comparedWith) {
        super(operator);
        this.comparedColumn = comparedColumn;
        this.comparedWith = comparedWith;
    }

    public static ComparisonNode of(Operator operator, Column comparedColumn, Object comparedWith) {
        return new ComparisonNode(operator, comparedColumn, comparedWith);
    }

    public Column getComparedColumn() {
        return comparedColumn;
    }

    public Object getComparedWith() {
        return comparedWith;
    }
}