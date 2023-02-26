package ua.aleh1s.hotelepam.querybuilder.node;

import ua.aleh1s.hotelepam.querybuilder.Operator;

public abstract class PredicateNode extends ExpressionNode {
    protected final Operator operator;
    protected PredicateNode(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }
}