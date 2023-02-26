package ua.aleh1s.hotelepam.model.querybuilder.specification.where;

public enum SearchOperation {
    EQUAL("="),
    NOT_EQUAL("!="),
    GREATER_THAN(">="),
    LESS_THAN("<=");

    private final String value;

    SearchOperation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}