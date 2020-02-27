package javafxapp.api.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import javafxapp.api.dto.UsrDto;
import javafxapp.api.dto.UsrResponseDto;
import lombok.NoArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@NoArgsConstructor
public class UsrDataApiDao extends BaseDataApiDao {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String USRS_ENDPOINT = "/users";

    public UsrResponseDto getAllUsrs(String token) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost(apiConfig.getGameApiBase())
                    .setPort(apiConfig.getGameApiPort())
                    .setPath(apiConfig.getGameApiPath() + USRS_ENDPOINT)
                    .build();

            // TODO: resolve token
            return objectMapper.readValue(getData(uri, token), UsrResponseDto.class);
        } catch (URISyntaxException | JsonProcessingException e) {
//            e.printStackTrace();
            log.error("IN getAllUsrs - Can`t get usrs list. %s", e);
            throw new IllegalStateException("Can`t get list of Users");
        }
    }
}
