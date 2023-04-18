package ua.aleh1s.hotelepam.database.querybuilder;

public enum Operator {
    EQUAL("="),
    NOT_EQUAL("!="),
    AND("and"),
    OR("or"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    BETWEEN("between"),
    IN("in"),
    NOT_IN("not in"),
    LIKE("like"),
    NOT_LIKE("not like"),
    NOT_BETWEEN("not between"),
    IS_NOT_NULL("is not null"),
    IS_NULL("is null");

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
