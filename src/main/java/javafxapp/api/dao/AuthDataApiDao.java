package javafxapp.api.dao;

import javafxapp.api.dto.AuthRequestDto;
import javafxapp.api.dto.AuthResponseDto;
import javafxapp.api.dto.RegisterRequestDto;
import javafxapp.api.dto.RegisterResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class AuthDataApiDao extends BaseDataApiDao {
    private static final String AUTH_PATH = "/api/v1/auth";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String REGISTER_ENDPOINT = "/register";

    public AuthResponseDto login(AuthRequestDto requestDto) {
        try {
            System.out.println(apiConfig.getGameApiBaseUrl());

            URI url = new URIBuilder(URI.create(apiConfig.getGameApiBaseUrl()))
                    .setPath(AUTH_PATH + LOGIN_ENDPOINT)
                    .build();

            String jsonData = objectMapper.writeValueAsString(requestDto);
            String data = postData(url, jsonData);

            System.out.println(String.format("IN login - request url %s", url.toString()));
            System.out.println(String.format("IN login - login response %s", data));

            return objectMapper.readValue(data, AuthResponseDto.class);

        } catch (URISyntaxException e) {
//            log.error(String.format("IN login - Can`t resolve url %s.", apiConfig.getGameApiBaseUrl()), e);
            System.err.println(String.format("IN login - Can`t resolve url %s.", apiConfig.getGameApiBaseUrl()));
            throw new IllegalStateException("Can`t resolve url");
        } catch (IOException e2) {
//            log.error(String.format("IN login - Can`t login, request %s.", requestDto.toString()), e2);
            System.err.println(String.format("IN login - Can`t login, request %s.", requestDto.toString()));
            throw new IllegalStateException("Can`t login User");
        }
    }

    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        try {
            URI url = new URIBuilder(URI.create(apiConfig.getGameApiBaseUrl()))
                    .setPath(AUTH_PATH + REGISTER_ENDPOINT)
                    .build();

            String jsonData = objectMapper.writeValueAsString(requestDto);
            String data = postData(url, jsonData);

            System.out.println(String.format("IN register - request url %s", url.toString()));
            System.out.println(String.format("IN register - login response %s", data));

            return objectMapper.readValue(data, RegisterResponseDto.class);
        } catch (URISyntaxException e) {
            log.error(String.format("IN register - Can`t resolve url %s.", apiConfig.getGameApiBaseUrl()), e);
            throw new IllegalStateException("Can`t resolve url");
        } catch (IOException e2) {
            log.error(String.format("IN register - Can`t login, request %s.", requestDto.toString()), e2);
            throw new IllegalStateException("Can`t register User");
        }
    }
}
