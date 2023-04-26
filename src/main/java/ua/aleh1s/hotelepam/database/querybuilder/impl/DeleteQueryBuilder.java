package ua.aleh1s.hotelepam.database.querybuilder.impl;


import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.node.PredicateNode;

import java.util.StringJoiner;

public class DeleteQueryBuilder<T> extends ConditionalQueryBuilder<T> {

    private static final String QUERY_BASE = "delete from \"%s\" ";

    public DeleteQueryBuilder(Root<T> root) {
        super(root, new StringJoiner(" ", String.format(QUERY_BASE, root.getTableName()), ";"));
    }

    @Override
    public DeleteQueryBuilder<T> where(PredicateNode predicateNode) {
        return (DeleteQueryBuilder<T>) super.where(predicateNode);
    }
}
