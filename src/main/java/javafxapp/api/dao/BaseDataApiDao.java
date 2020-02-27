package javafxapp.api.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafxapp.api.config.ApiConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public abstract class BaseDataApiDao {
    protected final ObjectMapper objectMapper = new ObjectMapper();
    protected final ApiConfig apiConfig = new ApiConfig();

    public String getData(URI uri, String token) {
        try {
            ContentType contentType = ContentType.APPLICATION_JSON;

            HttpUriRequest request = RequestBuilder.get()
                    .setUri(uri)
                    .setHeader(HttpHeaders.CONTENT_TYPE, contentType.toString())
                    .setHeader("auth", token)
                    .build();

            HttpClient client = HttpClients.createDefault();

            HttpResponse response = client.execute(request);
            HttpEntity responseEntity = response.getEntity();

            return EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String postData(URI uri, String data) {
        try{
            ContentType contentType = ContentType.APPLICATION_JSON;

            StringEntity requestEntity = new StringEntity(data, contentType);

            HttpPost postMethod = new HttpPost(uri);
            postMethod.setEntity(requestEntity);

            HttpClient client = HttpClients.createDefault();

            HttpResponse response = client.execute(postMethod);
            HttpEntity responseEntity = response.getEntity();

            return EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
