package ua.aleh1s.hotelepam;

import java.util.Objects;
import java.util.ResourceBundle;

import static ua.aleh1s.hotelepam.Constant.RESOURCES_PROPERTIES;

public class ResourcesManager {
    private static ResourcesManager INSTANCE;
    private final ResourceBundle resourceBundle;

    {
        resourceBundle = ResourceBundle.getBundle(RESOURCES_PROPERTIES);
    }

    public static synchronized ResourcesManager getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new ResourcesManager();

        return INSTANCE;
    }

    public String getValue(String key) {
        return resourceBundle.getString(key);
    }
}
