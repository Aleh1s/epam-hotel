package ua.aleh1s.hotelepam.querybuilder.entityconfiguration;

import java.util.Map;

public class EntityConfiguration {

    private String tableName;
    private Map<String, Column> columnMap;

    private EntityConfiguration(
            String tableName,
            Map<String, Column> columnMap) {
        this.tableName = tableName;
        this.columnMap = columnMap;
    }

    public static EntityConfiguration newConfiguration(String tableName, Map<String, Column> columnMap) {
        return new EntityConfiguration(tableName, columnMap);
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Column> getColumnMap() {
        return columnMap;
    }
}
