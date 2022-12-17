package ua.aleh1s.hotelepam.jdbc;

import java.util.Optional;

import static java.util.Objects.*;

public enum DatabaseManagerFactory {
    INSTANCE;
    private DatabaseManager databaseManager;
    public synchronized DatabaseManager getDatabaseManager() {
        if (isNull(databaseManager)) {
            Boolean isTest = Optional.ofNullable(System.getProperty("isTest"))
                    .map(Boolean::parseBoolean).orElseThrow(() -> new RuntimeException("You must specify isTest parameter"));
            if (isTest)
                databaseManager = new TestDatabaseManager();
            else
                databaseManager = new MainDatabaseManager();
        }
        return databaseManager;
    }
}