package javafxapp.api.config;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

@Getter
public class ApiConfig {
    private String gameApiBase;
    private String gameApiPath;
    private int gameApiPort;

    private String secret;

    public ApiConfig() {
        Properties defaultProps = new Properties();

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("default.properties")) {
            if (in != null) {
                defaultProps.load(in);
                gameApiBase = defaultProps.getProperty("gameapi.baseurl");
                gameApiPath = defaultProps.getProperty("gameapi.path");
                gameApiPort = Integer.parseInt(defaultProps.getProperty("gameapi.port"));

                secret = defaultProps.getProperty("gameapi.secret");

                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
