package ua.aleh1s.hotelepam.model.dao;

import ua.aleh1s.hotelepam.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.util.Objects.nonNull;

public abstract class DAO {
    protected Connection connection;

    public void close(Statement statement) throws DaoException {
        if (nonNull(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}