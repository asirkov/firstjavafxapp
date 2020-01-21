package javafxapp.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("id")
    private final Long id;

    @JsonProperty("login")
    private final String login;

    @JsonProperty("password_hash")
    private final String passwordHash;

    @JsonProperty("password_salt")
    private final String passwordSalt;
}
