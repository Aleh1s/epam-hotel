package ua.aleh1s.hotelepam.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ua.aleh1s.hotelepam.Constant.DATABASE_PROPERTIES;

public class DBManager {
    private static DBManager instance;
    private HikariDataSource dataSource;
    private DBManager() {
        ClassLoader classLoader = DBManager.class.getClassLoader();
        Optional<InputStream> optionalInputStream
                = Optional.ofNullable(classLoader.getResourceAsStream(DATABASE_PROPERTIES));
        if (optionalInputStream.isPresent()) {
            try (InputStream inputStream = optionalInputStream.get()) {
                Properties properties = new Properties();
                properties.load(inputStream);
                HikariConfig config = new HikariConfig(properties);
                this.dataSource = new HikariDataSource(config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized static DBManager getInstance() {
        if (isNull(instance))
            instance = new DBManager();
        return instance;
    }
    public synchronized static void shutdown() {
        if (nonNull(instance))
            instance.getDataSource().close();
    }
    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
