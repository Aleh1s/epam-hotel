package ua.aleh1s.hotelepam.database.querybuilder.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.database.jdbc.DBManager;
import ua.aleh1s.hotelepam.database.querybuilder.InsertionValue;
import ua.aleh1s.hotelepam.database.querybuilder.Parameter;
import ua.aleh1s.hotelepam.database.querybuilder.QueryBuilder;
import ua.aleh1s.hotelepam.database.querybuilder.Root;
import ua.aleh1s.hotelepam.database.querybuilder.entityconfiguration.Column;

import java.sql.*;
import java.util.StringJoiner;

public class InsertQueryBuilder<T> extends QueryBuilder<T> {

    private static final String QUERY_BASE = "insert into \"%s\" ";
    private static final Logger logger = LogManager.getLogger(InsertQueryBuilder.class);
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

    public Long executeAndReturnPrimaryKey() {
        long id = 0L;
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(build(), Statement.RETURN_GENERATED_KEYS)) {
                super.injectValues(statement);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id = generatedKeys.getLong(1);
                        }
                    }
                } else {
                    throw new RuntimeException();
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return id;
    }
}
