package javafxapp.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerModel {
    @JsonProperty("id")
    private final Long id;

    @JsonProperty("user_id")
    private final Long userId;

    @JsonProperty("player_name")
    private final String playerName;

    @JsonProperty("games")
    private final Long games;

    @JsonProperty("rate")
    private final Double rate;

    @JsonProperty("online")
    private final Boolean online;

//    @JsonProperty("avatar_id")
//    private final Integer avatarId;


}
