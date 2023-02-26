package ua.aleh1s.hotelepam.querybuilder.entityconfiguration;

import java.util.Objects;

public class Column {

    private final String name;
    private final int type;

    private Column(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public static Column of(String name, int type) {
        return new Column(name, type);
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return type == column.type && Objects.equals(name, column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
