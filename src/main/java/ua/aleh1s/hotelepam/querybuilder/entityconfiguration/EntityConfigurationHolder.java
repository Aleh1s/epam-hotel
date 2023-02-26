package ua.aleh1s.hotelepam.querybuilder.entityconfiguration;

import ua.aleh1s.hotelepam.model.entity.UserEntity;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class EntityConfigurationHolder {

    private static EntityConfigurationHolder instance;
    private final Map<Class<?>, EntityConfiguration> entityConfigurationMap;

    public static synchronized EntityConfigurationHolder getInstance() {
        if (isNull(instance))
            instance = new EntityConfigurationHolder();
        return instance;
    }

    {
        entityConfigurationMap = new HashMap<>();

        // UserEntity
        Column id = Column.of("id", Types.BIGINT);
        Column firstName = Column.of("first_name", Types.VARCHAR);
        Column lastName = Column.of("last_name", Types.VARCHAR);
        Column email = Column.of("email", Types.VARCHAR);

        Map<String, Column> columnMap = new HashMap<>();
        columnMap.put("id", id);
        columnMap.put("firstName", firstName);
        columnMap.put("lastName", lastName);
        columnMap.put("email", email);

        EntityConfiguration entityConfiguration
                = EntityConfiguration.newConfiguration("user", columnMap);
        entityConfigurationMap.put(UserEntity.class, entityConfiguration);
    }


    public <T> EntityConfiguration valueOf(Class<T> clazz) {
        return entityConfigurationMap.get(clazz);
    }
}
