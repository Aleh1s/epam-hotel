package ua.aleh1s.hotelepam.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.util.Objects.nonNull;

public abstract class DAO {
    protected Connection connection;

    public void close(Statement statement) {
        if (nonNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}