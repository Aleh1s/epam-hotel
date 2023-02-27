package ua.aleh1s.hotelepam.model.querybuilder;

import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;

public class InsertionValue {

    private final Column column;
    private final Object value;

    private InsertionValue(Column column, Object value) {
        this.column = column;
        this.value = value;
    }

    public static InsertionValue of(Column column, Object value) {
        return new InsertionValue(column, value);
    }

    public Column getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }
}
