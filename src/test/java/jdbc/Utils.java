package jdbc;

import java.sql.*;

public class Utils {

    public static void createTables(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SqlQuery.SQL_CREATE_ALL_TABLES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearDatabase(Connection connection) throws SQLException {
        try (PreparedStatement setChecks = connection.prepareStatement("SET REFERENTIAL_INTEGRITY = ?");
             PreparedStatement getTables = connection.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema = SCHEMA()")) {
            try (ResultSet tablesRes = getTables.executeQuery()) {
                setChecks.setBoolean(1, false);
                setChecks.executeUpdate();
                while (tablesRes.next()) {
                    String table = tablesRes.getString(1);
                    try (PreparedStatement truncateTable = connection.prepareStatement("TRUNCATE TABLE \"" + table + "\" RESTART IDENTITY")) {
                        truncateTable.executeUpdate();
                    }
                }
            } finally {
                setChecks.setBoolean(1, true);
                setChecks.executeUpdate();
            }
        }
    }
}
