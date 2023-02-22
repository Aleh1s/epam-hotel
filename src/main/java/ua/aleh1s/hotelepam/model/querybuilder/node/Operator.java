package ua.aleh1s.hotelepam.model.querybuilder.node;

public enum Operator {
    EQUAL("="),
    NOT_EQUAL("!="),
    AND("and"),
    OR("or"),
    GREATER_THAN(">="),
    LESS_THAN("<="),
    BETWEEN("between"),
    IN("in"),
    NOT_IN("not in");

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
