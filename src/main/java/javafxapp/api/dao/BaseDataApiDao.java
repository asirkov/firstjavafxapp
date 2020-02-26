package javafxapp.api.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

import static java.util.Objects.isNull;

public class BaseDataApiDao {
    protected ObjectMapper objectMapper = new ObjectMapper();


    public String getData(URIBuilder builder) {
        try {
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            ContentType contentType = ContentType.APPLICATION_JSON;
            request.addHeader(HttpHeaders.CONTENT_TYPE, contentType.toString());

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity responseEntity = response.getEntity();
                return isNull(responseEntity) ? "" : EntityUtils.toString(responseEntity, contentType.getCharset());
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
