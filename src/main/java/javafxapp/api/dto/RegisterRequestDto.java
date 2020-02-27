package javafxapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequestDto extends BaseResponseDto {
    private String username;
    private String playername;
    private String passwordHash;
    private byte[] avatarData;
}
