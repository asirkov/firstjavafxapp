package javafxapp.api.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ApiConfigTest {

    @Test
    public void testConfigRead() {
        ApiConfig apiConfig = ApiConfig.getInstance();
        Object config = apiConfig.getConfig("gameapi");

        Assertions.assertTrue(config instanceof Map);
    }

}