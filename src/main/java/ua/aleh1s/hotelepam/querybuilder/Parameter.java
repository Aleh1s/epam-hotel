package ua.aleh1s.hotelepam.querybuilder;

import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.Column;

public class Parameter {

    private final Column column;
    private final Object value;

    private Parameter(Column column, Object value) {
        this.column = column;
        this.value = value;
    }

    public static Parameter of(Column column, Object value) {
        return new Parameter(column, value);
    }

    public Column getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }
}
