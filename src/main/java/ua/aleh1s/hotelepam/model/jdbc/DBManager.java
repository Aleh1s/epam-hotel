package ua.aleh1s.hotelepam.model.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import static ua.aleh1s.hotelepam.constant.Application.DATABASE_PROPERTIES;

public class DBManager {
    private static final Logger logger = LogManager.getLogger(DBManager.class);
    private static DBManager INSTANCE;
    private DataSource dataSource;

    private DBManager() {
        logger.trace("DBManager initialization...");
        initialize();
        logger.trace("DBManager initialized");
    }

    public static synchronized DBManager getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new DBManager();
        return INSTANCE;
    }

    private void initialize() {
        logger.trace("Data source initialization...");
        Properties properties = loadProperties();
        HikariConfig config = new HikariConfig(properties);
        this.dataSource = new HikariDataSource(config);
        logger.trace("Data source initialized");
    }

    public synchronized void shutdown() {
        logger.trace("Shutdown data source is invoked");
        ((HikariDataSource) dataSource).close();
        logger.trace("Shutdown data source is completed");
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return connection;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error while database properties initialization");
        }
        return properties;
    }
}