package ua.aleh1s.hotelepam.model.querybuilder.impl;

import ua.aleh1s.hotelepam.model.querybuilder.InsertionValue;
import ua.aleh1s.hotelepam.model.querybuilder.Root;
import ua.aleh1s.hotelepam.model.querybuilder.Parameter;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.Column;
import ua.aleh1s.hotelepam.model.querybuilder.node.PredicateNode;

import java.util.StringJoiner;

public class UpdateQueryBuilder<T> extends ConditionalQueryBuilder<T> {

    private static final String QUERY_BASE = "update \"%s\" set ";

    public UpdateQueryBuilder(Root<T> root) {
        super(root, new StringJoiner(" ", String.format(QUERY_BASE, root.getTableName()), ";"));
    }

    @Override
    public UpdateQueryBuilder<T> where(PredicateNode predicateNode) {
        return (UpdateQueryBuilder<T>) super.where(predicateNode);
    }

    public UpdateQueryBuilder<T> set(InsertionValue... values) {

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (InsertionValue insertionValue : values) {
            Column column = insertionValue.getColumn();
            Object value = insertionValue.getValue();

            this.root.addParameter(Parameter.of(column, value));
            stringJoiner.add(String.format("%s = ?", column.getName()));
        }

        this.query.add(stringJoiner.toString());
        return this;
    }
}
