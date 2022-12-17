package ua.aleh1s.hotelepam.jdbc;

import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;

import javax.sql.DataSource;
import java.sql.Connection;

public abstract class DatabaseManager {
    protected DataSource dataSource;
    public abstract Connection getConnection() throws JdbcException;
    public abstract void shutdown() throws JdbcException;
}
