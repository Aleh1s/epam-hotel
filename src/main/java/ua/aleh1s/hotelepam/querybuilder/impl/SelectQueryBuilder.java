package ua.aleh1s.hotelepam.querybuilder.impl;

import ua.aleh1s.hotelepam.model.sqlmapper.SqlEntityMapper;
import ua.aleh1s.hotelepam.querybuilder.AggregateFunctionBuilder;
import ua.aleh1s.hotelepam.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.querybuilder.Root;
import ua.aleh1s.hotelepam.querybuilder.node.PredicateNode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SelectQueryBuilder<T> extends ConditionalQueryBuilder<T> {

    private static final String QUERY_BASE = "select * from \"%s\" ";
    private static final String QUERY_BASE_WITH_AGGREGATE_FUNC = "select %s from \"%s\" ";

    public SelectQueryBuilder(Root<T> root) {
        super(root, new StringJoiner(" ", String.format(QUERY_BASE, root.getTableName()), ";"));
    }

    public SelectQueryBuilder(Root<T> root, AggregateFunctionBuilder aggregateFunctionBuilder) {
        super(root, new StringJoiner(
                " ", String.format(
                        QUERY_BASE_WITH_AGGREGATE_FUNC, aggregateFunctionBuilder.build(), root.getTableName()), ";"));
    }

    @Override
    public SelectQueryBuilder<T> where(PredicateNode predicateNode) {
        return (SelectQueryBuilder<T>) super.where(predicateNode);
    }

    public SelectQueryBuilder<T> order(OrderUnit... units) {
        StringJoiner orderJoiner = new StringJoiner(", ", "order by ", "");
        for (OrderUnit unit : units)
            orderJoiner.add(unit.toString());
        this.query.add(orderJoiner.toString());
        return this;
    }

    public SelectQueryBuilder<T> limit(int limit) {
        this.query.add("limit").add(String.valueOf(limit));
        return this;
    }

    public SelectQueryBuilder<T> offset(int offset) {
        this.query.add("offset").add(String.valueOf(offset));
        return this;
    }

    public List<T> getResultList(SqlEntityMapper<T> mapper) throws SQLException {
        List<T> resultList = new ArrayList<>();
        String query = build();
        try (Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");) {
            try (PreparedStatement statement = postgres.prepareStatement(query)) {
                super.injectValues(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        resultList.add(mapper.apply(resultSet));
                    }
                }
            }
        }
        return resultList;
    }

    public T getResult(SqlEntityMapper<T> mapper) throws SQLException {
        T result = null;
        String query = build();
        try (Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");) {
            try (PreparedStatement statement = postgres.prepareStatement(query)) {
                super.injectValues(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        result = mapper.apply(resultSet);
                    }
                }
            }
        }
        return result;
    }
}
