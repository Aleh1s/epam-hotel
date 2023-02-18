package ua.aleh1s.hotelepam.model.dao;

import java.sql.Connection;

public abstract class DAO {
    protected Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}