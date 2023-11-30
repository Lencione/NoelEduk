package br.com.noeleduk.noelproject.controllers.auth;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static br.com.noeleduk.noelproject.commons.UserConstraints.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Test
    public void loginUser_withValidEmailAndPassword_returnsLoggedUser() {
        when(userService.login(LOGIN_REQUEST_DTO)).thenReturn(LOGGED_USER_DTO);

        ResponseEntity<ResponseDto> sut = authController.login(LOGIN_REQUEST_DTO);

        verify(userService, times(1)).login(LOGIN_REQUEST_DTO);
        assertThat(sut).isEqualTo(LOGIN_USER_RESPONSE);
    }

    @Test
    public void loginUser_withInvalidEmailAndPassword_returnsInvalidPassword() {
        when(userService.login(LOGIN_REQUEST_DTO)).thenThrow(new RuntimeException(INVALID_PASSWORD));

        ResponseEntity<ResponseDto> sut = authController.login(LOGIN_REQUEST_DTO);

        verify(userService, times(1)).login(LOGIN_REQUEST_DTO);
        assertThat(sut).isEqualTo(INVALID_LOGIN_USER_RESPONSE);
    }

    @Test
    public void createUser_withValidEmailAndPassword_returnsCreatedUser() {
        when(userService.create(CREATE_USER_DTO)).thenReturn(LOGGED_USER_DTO);

        ResponseEntity<ResponseDto> sut = authController.createUser(CREATE_USER_DTO);

        verify(userService, times(1)).create(CREATE_USER_DTO);
        assertThat(sut).isEqualTo(CREATE_USER_RESPONSE);
    }

    @Test
    public void createUser_withDuplicatedEmail_returnsDuplicatedEmail() {
        when(userService.create(CREATE_USER_DTO)).thenThrow(new RuntimeException(DUPLICATED_EMAIL));

        ResponseEntity<ResponseDto> sut = authController.createUser(CREATE_USER_DTO);

        verify(userService, times(1)).create(CREATE_USER_DTO);
        assertThat(sut).isEqualTo(DUPLICATED_EMAIL_CREATE_USER_RESPONSE);
    }
}
