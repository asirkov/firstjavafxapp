package javafxapp.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class LoginResponseDto {
    private final String username;
    private final String token;
}
