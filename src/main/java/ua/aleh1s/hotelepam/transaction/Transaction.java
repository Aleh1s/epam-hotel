package ua.aleh1s.hotelepam.transaction;

import ua.aleh1s.hotelepam.jdbc.DBManager;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;
import ua.aleh1s.hotelepam.model.dao.DAO;
import ua.aleh1s.hotelepam.transaction.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class Transaction implements AutoCloseable {
    private final DBManager dbManager;
    private Connection connection;

    private Transaction() {
        this.dbManager = DBManager.getInstance();
    }

    public static Transaction start(DAO dao, DAO... daos) {
        Transaction transaction = new Transaction();
        transaction.initDaos(dao, daos);
        return transaction;
    }

    private void initDaos(DAO dao, DAO... daos) {
        try {
            openConnection();
            setAutoCommit(false);
            injectConnections(dao, daos);
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            setAutoCommit(true);
            closeConnection();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback()  {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
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
                this.connection = dbManager.getConnection();
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

    private void injectConnections(DAO dao, DAO... daos) {
        dao.setConnection(connection);
        for (DAO d : daos) {
            d.setConnection(connection);
        }
    }
}
