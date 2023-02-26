package ua.aleh1s.hotelepam.querybuilder.impl;


import ua.aleh1s.hotelepam.querybuilder.QueryBuilder;
import ua.aleh1s.hotelepam.querybuilder.Root;

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
