package ua.aleh1s.hotelepam.appcontext;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static ua.aleh1s.hotelepam.constant.Application.RESOURCES_PROPERTIES;

public class ResourcesManager {
    private static ResourcesManager INSTANCE;
    private final Properties properties;

    {
        Properties properties = new Properties();
        try {
            properties.load(
                    ResourcesManager.class.getClassLoader().getResourceAsStream(RESOURCES_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    public static synchronized ResourcesManager getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new ResourcesManager();

        return INSTANCE;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
