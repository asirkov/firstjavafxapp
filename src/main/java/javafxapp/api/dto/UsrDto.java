package javafxapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsrDto extends BaseEntityDto {
    private String username;
    private String player;

    private Long games;
    private Long wins;
    private Long loses;

    private boolean online;
}
