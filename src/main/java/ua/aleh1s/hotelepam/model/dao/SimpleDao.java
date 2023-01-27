package ua.aleh1s.hotelepam.model.dao;

import ua.aleh1s.hotelepam.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static java.util.Objects.nonNull;

public abstract class SimpleDao<K, E> {
    protected Connection connection;
    public abstract Optional<E> findBy(K id) throws DaoException;
    public abstract int delete(E entity) throws DaoException;
    public abstract int update(E entity) throws DaoException;
    public abstract E save(E entity) throws DaoException;
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