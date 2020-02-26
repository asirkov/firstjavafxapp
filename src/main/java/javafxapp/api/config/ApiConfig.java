package javafxapp.api.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApiConfig {
    private static Map<String, Object> CONFIG = null;

    private final String gameApiBase;
    private final String gameApiPath;
    private final int gameApiPort;

    private ApiConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("application.yaml");
        CONFIG = yaml.load(inputStream);

        @SuppressWarnings("unchecked")
        Map<String, String> gameApiConfig = (HashMap<String, String>) getConfig("gameapi");

        gameApiBase = gameApiConfig.getOrDefault("base", null);
        gameApiPath = gameApiConfig.getOrDefault("path", null);
//        gameApiPort = Optional.ofNullable(gameApiConfig.getOrDefault("port", ""))
//                .map(Ints::tryParse)
//                .orElse(0);
        gameApiPort = 8888;
    }

    private static ApiConfig INSTANCE = null;

    public static ApiConfig getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ApiConfig();
        return INSTANCE;
    }

    Object getConfig(String key) {
        return CONFIG.getOrDefault(key, null);
    }

    public String getGameApiBase() {
        return gameApiBase;
    }

    public String getGameApiPath() {
        return gameApiPath;
    }

    public int getGameApiPort() {
        return gameApiPort;
    }
}
