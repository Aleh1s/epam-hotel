package ua.aleh1s.hotelepam.database.querybuilder.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.database.jdbc.DBManager;
import ua.aleh1s.hotelepam.database.querybuilder.OrderUnit;
import ua.aleh1s.hotelepam.database.querybuilder.QueryBuilderException;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.aggregatefunc.AggregateFunctionBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.node.PredicateNode;
import ua.aleh1s.hotelepam.database.querybuilder.ColumnDecorator;
import ua.aleh1s.hotelepam.mapper.sqlmapper.SqlEntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SelectQueryBuilder<T> extends ConditionalQueryBuilder<T> {

    private static final Logger logger = LogManager.getLogger(SelectQueryBuilder.class);
    private static final String QUERY_BASE = "select * from \"%s\" ";
    private static final String QUERY_BASE_WITH_AGGREGATE_FUNC = "select %s from \"%s\" ";
    private static final String QUERY_BASE_WITH_ONE_VALUE = "select %s from \"%s\" ";

    public SelectQueryBuilder(Root<T> root) {
        super(root, new StringJoiner(" ", String.format(QUERY_BASE, root.getTableName()), ";"));
    }

    public SelectQueryBuilder(Root<T> root, AggregateFunctionBuilder aggregateFunctionBuilder) {
        super(root, new StringJoiner(
                " ", String.format(
                QUERY_BASE_WITH_AGGREGATE_FUNC, aggregateFunctionBuilder.build(), root.getTableName()), ";"));
    }

    public SelectQueryBuilder(Root<T> root, ColumnDecorator columnDecorator) {
        super(root, new StringJoiner(" ", String.format(
                QUERY_BASE_WITH_ONE_VALUE, columnDecorator.getColumn().getName(), root.getTableName()
        ), ";"));
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

    public List<T> getResultList(SqlEntityMapper<T> mapper) {
        List<T> resultList = new ArrayList<>();
        String query = build();
        logger.info(query);
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                super.injectValues(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        resultList.add(mapper.apply(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new QueryBuilderException(e);
        }
        return resultList;
    }

    public T getResult(SqlEntityMapper<T> mapper) {
        T result = null;
        String query = build();
        logger.info(query);
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                super.injectValues(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        result = mapper.apply(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new QueryBuilderException(e);
        }
        return result;
    }

    public byte[] getBytes() {
        byte[] result = new byte[0];
        String query = build();
        logger.info(query);
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                super.injectValues(statement);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        result = resultSet.getBytes(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new QueryBuilderException(e);
        }
        return result;
    }
}
