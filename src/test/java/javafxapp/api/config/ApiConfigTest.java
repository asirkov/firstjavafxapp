package javafxapp.api.config;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApiConfigTest {

    @Test
    public void testConfigRead() {
        ApiConfig apiConfig = new ApiConfig();
        String baseUrl = apiConfig.getGameApiBaseUrl();

        assertEquals("http://127.0.0.1:8888/api/v1", baseUrl);
    }

    @Test
    public void testUriCreating() throws URISyntaxException {
        String urlString = "http://127.0.0.1:8888/api/v1";

        URI uri1 = new URIBuilder(urlString).build();
        URI uri2 = new URI(urlString);

        assertEquals(uri1.toString(), urlString);
        assertEquals(uri2.toString(), urlString);
    }

}