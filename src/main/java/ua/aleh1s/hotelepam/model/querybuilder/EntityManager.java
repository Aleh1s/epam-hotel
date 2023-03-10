package ua.aleh1s.hotelepam.model.querybuilder;

import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.EntityConfiguration;
import ua.aleh1s.hotelepam.model.querybuilder.entityconfiguration.EntityConfigurationHolder;

public class EntityManager {

    private static final EntityConfigurationHolder configurationHolder =
            EntityConfigurationHolder.getInstance();

    public <T> Root<T> valueOf(Class<T> clazz) {
        EntityConfiguration ec = configurationHolder.valueOf(clazz);
        return new Root<>(ec.getTableName(), ec.getColumnMap());
    }
}
