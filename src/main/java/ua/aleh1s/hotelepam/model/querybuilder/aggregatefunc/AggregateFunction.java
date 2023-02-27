package ua.aleh1s.hotelepam.model.querybuilder.aggregatefunc;

public enum AggregateFunction {

    COUNT("count(%s)"),
    COUNT_ALL("count(*)");

    private final String value;

    AggregateFunction(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
