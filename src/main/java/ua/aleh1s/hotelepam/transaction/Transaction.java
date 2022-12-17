package ua.aleh1s.hotelepam.transaction;

import ua.aleh1s.hotelepam.jdbc.DatabaseManager;
import ua.aleh1s.hotelepam.jdbc.DatabaseManagerFactory;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;
import ua.aleh1s.hotelepam.model.dao.SimpleDao;
import ua.aleh1s.hotelepam.transaction.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class Transaction implements AutoCloseable {
    private final DatabaseManager databaseManager;
    private Connection connection;

    private Transaction() {
        this.databaseManager = DatabaseManagerFactory.INSTANCE.getDatabaseManager();
    }

    public static Transaction start(SimpleDao<?, ?> dao, SimpleDao<?, ?>... daos)
            throws TransactionException {
        Transaction transaction = new Transaction();
        transaction.initDaos(dao, daos);
        return transaction;
    }

    private void initDaos(SimpleDao<?, ?> dao, SimpleDao<?, ?>... daos)
            throws TransactionException {
        openConnection();
        setAutoCommit(false);
        injectConnections(dao, daos);
    }

    @Override
    public void close() throws TransactionException {
        setAutoCommit(true);
        closeConnection();
    }

    public void commit() throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    public void rollback() throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    private void closeConnection() throws TransactionException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
        this.connection = null;
    }

    private void openConnection() throws TransactionException {
        if (isNull(connection)) {
            try {
                this.connection = databaseManager.getConnection();
            } catch (JdbcException e) {
                throw new TransactionException(e);
            }
        }
    }

    private void setAutoCommit(boolean value) throws TransactionException {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    private void injectConnections(SimpleDao<?, ?> dao, SimpleDao<?, ?>... daos) {
        dao.setConnection(connection);
        for (SimpleDao<?, ?> d : daos) {
            d.setConnection(connection);
        }
    }
}
