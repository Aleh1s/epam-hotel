package ua.aleh1s.hotelepam.model.querybuilder.specification;

public class Column {

    private final String name;
    private final int type;

    public Column(String name, int type) {
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
}
