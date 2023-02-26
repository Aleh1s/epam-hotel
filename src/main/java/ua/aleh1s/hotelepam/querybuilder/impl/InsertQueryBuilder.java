package ua.aleh1s.hotelepam.querybuilder.impl;

import ua.aleh1s.hotelepam.querybuilder.InsertionValue;
import ua.aleh1s.hotelepam.querybuilder.Parameter;
import ua.aleh1s.hotelepam.querybuilder.QueryBuilder;
import ua.aleh1s.hotelepam.querybuilder.Root;
import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.Column;
import java.util.StringJoiner;

public class InsertQueryBuilder<T> extends QueryBuilder<T> {

    private static final String QUERY_BASE = "insert into \"%s\" ";

    public InsertQueryBuilder(Root<T> root) {
        super(root, new StringJoiner(" ", String.format(QUERY_BASE, root.getTableName()), ";"));
    }

    public InsertQueryBuilder<T> values(InsertionValue... values) {
        StringJoiner columnNames = new StringJoiner(", ", "(", ")");
        StringJoiner questionMarks = new StringJoiner(", ", "(", ")");

        for (InsertionValue insertionValue : values) {
            Column column = insertionValue.getColumn();
            Object value = insertionValue.getValue();

            columnNames.add(column.getName());
            questionMarks.add("?");
            this.root.addParameter(Parameter.of(column, value));
        }

        this.query.add(columnNames.toString())
                .add("values")
                .add(questionMarks.toString());

        return this;
    }
}
