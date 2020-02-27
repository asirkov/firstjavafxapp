package javafxapp.api.dao;

import javafxapp.api.dto.*;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthDataApiDao extends BaseDataApiDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String LOGIN_ENDPOINT = "/auth/login";
    private static final String REGISTER_ENDPOINT = "/auth/register";

    public LoginResponseDto login(LoginRequestDto requestDto) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(apiConfig.getGameApiBase())
                    .setPort(apiConfig.getGameApiPort())
                    .setPath(apiConfig.getGameApiPath() + LOGIN_ENDPOINT)
                    .build();


            String jsonData = objectMapper.writeValueAsString(requestDto);
            String data = postData(uri, jsonData);

            return objectMapper.readValue(data, LoginResponseDto.class);
        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
            log.error(String.format("IN login - Can`t login, request %s.", requestDto.toString()), e);
            throw new IllegalStateException("Can`t login User");
        }
    }

    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(apiConfig.getGameApiBase())
                    .setPort(apiConfig.getGameApiPort())
                    .setPath(apiConfig.getGameApiPath() + REGISTER_ENDPOINT)
                    .build();


            String jsonData = objectMapper.writeValueAsString(requestDto);
            String data = postData(uri, jsonData);

            return objectMapper.readValue(data, RegisterResponseDto.class);
        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
            log.error(String.format("IN login - Can`t register, request %s.", requestDto.toString()), e);
            throw new IllegalStateException("Can`t register User");
        }
    }
}
