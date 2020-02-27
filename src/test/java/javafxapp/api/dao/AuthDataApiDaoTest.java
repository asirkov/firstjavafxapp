package javafxapp.api.dao;

import javafxapp.api.dto.LoginRequestDto;
import javafxapp.api.dto.LoginResponseDto;
import javafxapp.api.security.ApiPasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDataApiDaoTest {
    private final AuthDataApiDao authDataApiDao = new AuthDataApiDao();
    private final ApiPasswordEncoder apiPasswordEncoder = new ApiPasswordEncoder();

    @Test
    public void loginTest() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("admin", apiPasswordEncoder.sha256("admin"));

        LoginResponseDto loginResponseDto = authDataApiDao.login(loginRequestDto);

        Assertions.assertTrue(loginResponseDto.isAuthorized());
    }

}