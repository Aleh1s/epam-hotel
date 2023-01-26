package ua.aleh1s.hotelepam.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.jdbc.exception.JdbcException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import static ua.aleh1s.hotelepam.Constant.DATABASE_PROPERTIES;

public class DBManager {
    private static final Logger LOGGER = LogManager.getLogger(DBManager.class);
    private static DBManager INSTANCE;
    private DataSource dataSource;

    private DBManager() {
        LOGGER.trace("DBManager initialization...");
        initialize();
        LOGGER.trace("DBManager initialized");
    }

    public static synchronized DBManager getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new DBManager();
        return INSTANCE;
    }

    private void initialize() {
        LOGGER.trace("Data source initialization...");
        Properties properties = loadProperties();
        HikariConfig config = new HikariConfig(properties);
        this.dataSource = new HikariDataSource(config);
        LOGGER.trace("Data source initialized");
    }

    public synchronized void shutdown() {
        LOGGER.trace("Shutdown data source is invoked");
        ((HikariDataSource) dataSource).close();
        LOGGER.trace("Shutdown data source is completed");
    }

    public synchronized Connection getConnection() throws JdbcException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
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