package ua.aleh1s.hotelepam.querybuilder;

import ua.aleh1s.hotelepam.jdbc.DBManager;
import ua.aleh1s.hotelepam.querybuilder.entityconfiguration.Column;

import java.sql.*;
import java.util.StringJoiner;

public abstract class QueryBuilder<T> {

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

    public void execute() throws SQLException {
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(build())) {
                injectValues(statement);
                statement.executeUpdate();
            }
        }
    }

    public <R> R execute(Class<R> clazz) throws SQLException {
        String query = build();
        System.out.println(query);
        R result = null;
        try (Connection postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");) {
            try (PreparedStatement statement = postgres.prepareStatement(query)) {
                injectValues(statement);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                       result = resultSet.getObject(1, clazz);
                    }
                }
            }
        }
        return result;
    }
}