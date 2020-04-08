package javafxapp.api.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ApiConfig {
    private String gameApiBaseUrl;
    private String secret;

    public ApiConfig() {
        Properties defaultProps = new Properties();

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("default.properties")) {
            if (in != null) {
                defaultProps.load(in);
                gameApiBaseUrl = defaultProps.getProperty("gameapi.baseurl");
                secret = defaultProps.getProperty("gameapi.secret");

                System.out.println("Config successfully loaded");
            } else {
                throw new IOException("Can`t read the default properties file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
