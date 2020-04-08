package javafxapp.api.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafxapp.api.dto.UsrDto;
import javafxapp.api.dto.UsrResponseDto;
import javafxapp.api.dto.UsrsListResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@Slf4j
@NoArgsConstructor
public class UsrDataApiDao extends BaseDataApiDao {
    private static final String USRS_PATH = "/api/v1/";
    private static final String USRS_ENDPOINT = "users";
    private static final String AVATAR_ENDPOINT = "avatar";

    public UsrsListResponseDto getAllUsrs(String token) {
        try {
            URI url = new URIBuilder(URI.create(apiConfig.getGameApiBaseUrl()))
                    .setPath(USRS_PATH + USRS_ENDPOINT)
                    .build();

            System.out.println(url.toString());

            // TODO: resolve token
            final String data = getData(url, token);
            return objectMapper.readValue(data, UsrsListResponseDto.class);
        } catch (URISyntaxException | JsonProcessingException e) {
            log.error("IN getAllUsrs - Can`t get usrs list. %s", e);
            throw new IllegalStateException("Can`t get list of Users");
        }
    }

    public UsrsListResponseDto getAllOnlineUsrs(String token) {
        try {
            URI url = new URIBuilder(URI.create(apiConfig.getGameApiBaseUrl()))
                    .setPath(USRS_PATH + USRS_ENDPOINT)
                    .addParameter("online", "true")
                    .build();

            System.out.println(url.toString());

            // TODO: resolve token
            final String data = getData(url, token);
            return objectMapper.readValue(data, UsrsListResponseDto.class);
        } catch (URISyntaxException | JsonProcessingException e) {
            log.error("IN getAllOnlineUsrs - Can`t get online usrs list. %s", e);
            throw new IllegalStateException("Can`t get list of online  Users");
        }
    }

    public UsrResponseDto getUsrById(long usrId, String token) {
        try {
            URI url = new URIBuilder(URI.create(apiConfig.getGameApiBaseUrl()))
                    .setPath(USRS_PATH + USRS_ENDPOINT + "/" + String.valueOf(usrId))
                    .build();

            System.out.println(url.toString());

            // TODO: resolve token

            final String data = getData(url, token);

            return objectMapper.readValue(data, UsrResponseDto.class);
        } catch (URISyntaxException | JsonProcessingException e) {
            log.error("IN getUsrById - Can`t get usr. %s", e);
            e.printStackTrace();
            throw new IllegalStateException("Can`t get usr by id");
        }
    }


    public byte[] getUsrAvatarById(long usrId, String token) {
        try {
            URI url = new URIBuilder(URI.create(apiConfig.getGameApiBaseUrl()))
                    .setPath(USRS_PATH + USRS_ENDPOINT + "/" + String.valueOf(usrId) + "/" + AVATAR_ENDPOINT)
                    .build();

            System.out.println(url.toString());

            // TODO: resolve token

            final String data = getData(url, token);
            final byte[] bytes = Base64.getDecoder().decode(data);
            return bytes;
        } catch (URISyntaxException e) {
            log.error("IN getUsrAvatarById - Can`t get usr avatar. %s", e);
            e.printStackTrace();
            throw new IllegalStateException("Can`t get usr avatar by id");
        }
    }

    public UsrsListResponseDto getUsrByUsrsListByName(String usrName, String token) {
        // TODO: fix it
        return null;
    }
}
