package javafxapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsrResponseDto extends BaseResponseDto {
    private long totalCount;
    private List<UsrDto> users;
}
