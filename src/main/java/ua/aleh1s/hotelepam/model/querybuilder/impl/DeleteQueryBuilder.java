package ua.aleh1s.hotelepam.model.querybuilder.impl;


import ua.aleh1s.hotelepam.model.querybuilder.QueryBuilder;
import ua.aleh1s.hotelepam.model.querybuilder.Root;

import java.util.StringJoiner;

public class DeleteQueryBuilder<T> extends QueryBuilder<T> {
    public DeleteQueryBuilder(Root<T> root) {
        super(root, new StringJoiner(""));
    }

    @Override
    public String build() {
        return null;
    }
}
