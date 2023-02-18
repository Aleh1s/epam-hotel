package ua.aleh1s.hotelepam.transaction;

import ua.aleh1s.hotelepam.jdbc.DBManager;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;
import ua.aleh1s.hotelepam.model.dao.DAO;

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
        openConnection();
        setAutoCommit(false);
        injectConnections(dao, daos);
    }

    @Override
    public void close() {
        setAutoCommit(true);
        closeConnection();
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = null;
    }

    private void openConnection() {
        if (isNull(connection)) {
            try {
                this.connection = dbManager.getConnection();
            } catch (JdbcException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAutoCommit(boolean value) {
        try {
            if (connection != null) {
                connection.setAutoCommit(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void injectConnections(DAO dao, DAO... daos) {
        dao.setConnection(connection);
        for (DAO d : daos) {
            d.setConnection(connection);
        }
    }
}
