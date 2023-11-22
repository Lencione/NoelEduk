package br.com.noeleduk.noelproject.controllers.auth;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.LoggedUserDto;
import br.com.noeleduk.noelproject.dto.user.LoginRequestDto;
import br.com.noeleduk.noelproject.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Test
    public void testLoginSuccess() {
        LoginRequestDto loginRequest = new LoginRequestDto();
        LoggedUserDto loggedUserDto = new LoggedUserDto();

        when(userService.login(loginRequest)).thenReturn(loggedUserDto);

        ResponseEntity<ResponseDto> response = authController.login(loginRequest);
        verify(userService, times(1)).login(loginRequest);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getMessage().equals("User found");
        assert response.getBody().getData().equals(loggedUserDto);
    }

    @Test
    public void testLoginFailure() {
        LoginRequestDto loginRequest = new LoginRequestDto(); // preencha com os detalhes de login apropriados

        when(userService.login(loginRequest)).thenThrow(new RuntimeException("Login failed"));

        ResponseEntity<ResponseDto> response = authController.login(loginRequest);

        // Verifique se o serviço de usuário foi chamado corretamente
        verify(userService, times(1)).login(loginRequest);

        // Verifique se a resposta HTTP está correta
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;

        // Verifique o conteúdo da resposta
        assert response.getBody() != null;
//        assert !response.getBody().isSuccess();
        assert response.getBody().getMessage().equals("Login failed");
    }

    @Test
    public void testCreateUserSuccess() {
        CreateUserDto createUserDto = new CreateUserDto();
        LoggedUserDto loggedUserDto = new LoggedUserDto();

        when(userService.create(createUserDto)).thenReturn(loggedUserDto);

        ResponseEntity<ResponseDto> response = authController.createUser(createUserDto);

        // Verifique se o serviço de usuário foi chamado corretamente
        verify(userService, times(1)).create(createUserDto);

        // Verifique se a resposta HTTP está correta
        assert response.getStatusCode() == HttpStatus.OK;

        // Verifique o conteúdo da resposta
        assert response.getBody() != null;
        assert response.getBody().getMessage().equals("User created with success!");
    }

    @Test
    public void testCreateUserFailure() {
        CreateUserDto createUserDto = new CreateUserDto(); // preencha com os detalhes do usuário apropriados

        when(userService.create(createUserDto)).thenThrow(new RuntimeException("User creation failed"));

        ResponseEntity<ResponseDto> response = authController.createUser(createUserDto);

        // Verifique se o serviço de usuário foi chamado corretamente
        verify(userService, times(1)).create(createUserDto);

        // Verifique se a resposta HTTP está correta
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;

        // Verifique o conteúdo da resposta
        assert response.getBody() != null;
        assert response.getBody().getMessage().equals("User creation failed");
    }
}
