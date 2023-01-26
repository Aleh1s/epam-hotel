package ua.aleh1s.hotelepam.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static ua.aleh1s.hotelepam.Constant.DATABASE_PROPERTIES;

public class MainDatabaseManager extends DatabaseManager {
    private static final Logger LOGGER = LogManager.getLogger(MainDatabaseManager.class);

    MainDatabaseManager() {
        LOGGER.trace("DBManager initialization...");
        initDataSource();
        LOGGER.trace("DBManager initialized");
    }

    public synchronized void shutdown() {
        LOGGER.trace("Shutdown data source is invoked");
        ((HikariDataSource) dataSource).close();
        LOGGER.trace("Shutdown data source is completed");
    }

    public Connection getConnection() throws JdbcException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    private void initDataSource() {
        LOGGER.trace("Data source initialization...");
        Properties properties = loadProperties();
        HikariConfig config = new HikariConfig(properties);
        this.dataSource = new HikariDataSource(config);
        LOGGER.trace("Data source initialized");
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Error while database properties initialization");
        }
        return properties;
    }
}