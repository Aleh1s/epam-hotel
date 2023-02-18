package ua.aleh1s.hotelepam;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

import static ua.aleh1s.hotelepam.constant.Property.RESOURCES_PROPERTIES;

public class ResourcesManager {
    private static ResourcesManager INSTANCE;
    private Map<String, Object> root;

    {
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = ResourcesManager.class.getClassLoader()
                    .getResourceAsStream(RESOURCES_PROPERTIES);
            if (inputStream != null) {
                root = yaml.load(inputStream);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static synchronized ResourcesManager getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new ResourcesManager();

        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public String getValue(String key) {
        String[] split = key.split("\\.");
        Map<String, Object> map = root;
        for (int i = 0; i < split.length; i++) {
            if (i == (split.length - 1)) {
                return (String) map.get(split[i]);
            }
            map = (Map<String, Object>) map.get(split[i]);
        }
        return "";
    }
}
