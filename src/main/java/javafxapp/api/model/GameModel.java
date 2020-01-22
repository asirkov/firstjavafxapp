package javafxapp.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameModel {
    @JsonProperty("id")
    private final Long id;

    @JsonProperty("start_time")
    private final Date startTime;

    // WHITE
    @JsonProperty("participant_1_id")
    private final Long participant1Id;

    private final PlayerModel participant1;

    // BLACK
    @JsonProperty("participant_2_id")
    private final Long participant2Id;

    private final PlayerModel participant2;

    @JsonProperty("game_result")
    private final GameResultType gameResultType;

    // TODO: add score
    //
    //
}
