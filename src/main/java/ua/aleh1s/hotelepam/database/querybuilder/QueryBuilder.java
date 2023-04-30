package ua.aleh1s.hotelepam.database.querybuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.database.jdbc.DBManager;
import ua.aleh1s.hotelepam.database.querybuilder.entityconfiguration.Column;

import java.sql.*;
import java.util.StringJoiner;

public abstract class QueryBuilder<T> {

    private static final Logger logger = LogManager.getLogger(QueryBuilder.class);
    protected final Root<T> root;
    protected final StringJoiner query;

    protected QueryBuilder(Root<T> root, StringJoiner query) {
        this.root = root;
        this.query = query;
    }

    public String build() {
        return query.toString();
    }

    protected void injectValues(PreparedStatement statement) throws SQLException {
        int counter = 1;
        for (Parameter parameter : root.getParameters()) {
            Column column = parameter.getColumn();
            Object value = parameter.getValue();

            statement.setObject(counter++, value, column.getType());
        }
    }

    public void execute() {
        String query = build();
        logger.info(query);
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                injectValues(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new QueryBuilderException(e);
        }
    }

    public <R> R execute(Class<R> clazz) {
        R result = null;
        String query = build();
        logger.info(query);
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                injectValues(statement);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getObject(1, clazz);
                    }
                }
            }
        } catch (SQLException e) {
            throw new QueryBuilderException(e);
        }
        return result;
    }
}