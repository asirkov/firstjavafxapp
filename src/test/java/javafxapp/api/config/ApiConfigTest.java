package javafxapp.api.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ApiConfigTest {

    @Test
    public void testConfigRead() {
        ApiConfig apiConfig = new ApiConfig();
        String baseUrl = apiConfig.getGameApiBase();

        Assertions.assertEquals("127.0.0.1", baseUrl);
    }

}