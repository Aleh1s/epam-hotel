package ua.aleh1s.hotelepam.model.queryspecification.node;

public class Predicate extends ExpressionNode {
    private final ExpressionNode[] operands;
    private final Operator operator;

    private Predicate(
            ExpressionNode[] operands,
            Operator operator
    ) {
        this.operands = operands;
        this.operator = operator;
    }

    public static Predicate of(Operator operator, ExpressionNode... operands) {
        return new Predicate(operands, operator);
    }

    public ExpressionNode[] getOperands() {
        return operands;
    }

    public Operator getOperator() {
        return operator;
    }
}