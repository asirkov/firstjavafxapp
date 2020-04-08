package javafxapp.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class AuthRequestDto {
    private final String usrName;
    private final String password;
}
