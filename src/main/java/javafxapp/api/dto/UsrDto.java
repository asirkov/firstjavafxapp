package javafxapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
        {"id", "playerName","gamesCount", "winsCount", "losesCount", "online", "createdAt", "updatedAt" })
public class UsrDto extends BaseDto{
    private String playerName;

    private Long gamesCount;
    private Long winsCount;
    private Long losesCount;

    private boolean online;

    public UsrDto() {
    }
}
