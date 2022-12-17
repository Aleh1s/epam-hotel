package ua.aleh1s.hotelepam.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static ua.aleh1s.hotelepam.Constant.TEST_DATABASE_PROPERTIES;
import static ua.aleh1s.hotelepam.jdbc.constant.PropertyFieldNames.*;

public class TestDatabaseManager extends DatabaseManager {

    private static final Logger LOGGER = LogManager.getLogger(TestDatabaseManager.class);

    TestDatabaseManager() {
        LOGGER.trace("DBManagerForTesting initializing...");
        initDataSource();
        LOGGER.trace("DBManagerForTesting initialized");
    }

    @Override
    public Connection getConnection() throws JdbcException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public synchronized void shutdown() throws JdbcException {
        LOGGER.trace("Shutdown data source is invoked");
        // do nothing
        LOGGER.trace("Shutdown data source is completed");
    }

    private void initDataSource() {
        LOGGER.trace("Data source for testing initialization...");
        Properties properties = loadProperties();
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(properties.getProperty(JDBC_URL));
        dataSource.setUser(properties.getProperty(USERNAME));
        dataSource.setPassword(properties.getProperty(PASSWORD));
        this.dataSource = dataSource;
        LOGGER.trace("Data source for testing initialized");
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(TEST_DATABASE_PROPERTIES));
        } catch (IOException e) {
            LOGGER.error("Error while test database properties initialization");
        }
        return properties;
    }
}
