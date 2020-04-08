package javafxapp.api.dao;

import javafxapp.api.dto.AuthRequestDto;
import javafxapp.api.dto.AuthResponseDto;
import javafxapp.api.security.ApiPasswordEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthDataApiDaoTest {
    private final AuthDataApiDao authDataApiDao = new AuthDataApiDao();
    private final ApiPasswordEncoder apiPasswordEncoder = new ApiPasswordEncoder();

    @Test
    public void loginTest() {
        AuthRequestDto authRequestDto = new AuthRequestDto("admin", apiPasswordEncoder.sha256("admin"));

        AuthResponseDto authResponseDto = authDataApiDao.login(authRequestDto);

        Assertions.assertTrue(authResponseDto.getAuthorized());
    }

    @Test
    public void login() {


    }

    @Test
    public void register() {
    }
}