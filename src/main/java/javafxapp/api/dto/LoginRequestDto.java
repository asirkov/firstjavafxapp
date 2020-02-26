package javafxapp.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class LoginRequestDto {
    private final String username;
    private final String password;
}
