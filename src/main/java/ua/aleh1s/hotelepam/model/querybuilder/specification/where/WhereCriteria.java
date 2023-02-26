package ua.aleh1s.hotelepam.model.querybuilder.specification.where;

import ua.aleh1s.hotelepam.model.querybuilder.specification.Column;

public class WhereCriteria {

    private final Column column;
    private final Object value;
    private final SearchOperation operation;

    private WhereCriteria(
            Column column,
            Object value,
            SearchOperation operation) {
        this.column = column;
        this.value = value;
        this.operation = operation;
    }

    public Column getKey() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public static WhereCriteria of(Column column, Object value, SearchOperation operation) {
        return new WhereCriteria(column, value, operation);
    }
}
