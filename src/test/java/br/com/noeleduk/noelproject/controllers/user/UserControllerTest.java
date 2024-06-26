package br.com.noeleduk.noelproject.controllers.user;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static br.com.noeleduk.noelproject.commons.ClassConstraints.*;
import static br.com.noeleduk.noelproject.commons.UserConstraints.USER_NOT_FOUND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void getAllUsers_withValidData_returnsAllUsers() {
        when(userService.getAllUsers()).thenReturn(GET_USER_DTO_LIST);

        ResponseEntity<ResponseDto> sut = userController.getAllUsers();

        verify(userService, times(1)).getAllUsers();
        assertThat(sut).isEqualTo(GET_ALL_USERS_RESPONSE);
    }

    @Test
    void getAllUsers_withUsersNotFound_returnsNotFound() {
        when(userService.getAllUsers()).thenThrow(new RuntimeException(USERS_NOT_FOUND));

        ResponseEntity<ResponseDto> sut = userController.getAllUsers();

        verify(userService, times(1)).getAllUsers();
        assertThat(sut).isEqualTo(NOT_FOUND_USERS_RESPONSE);
    }

    @Test
    public void getStudentLessons_withValidData_returnsAllUsers() {
        when(userService.getStudentLessons(MOCK_DOCUMENT_1)).thenReturn(GET_FORMATTED_USER_LESSONS_DTO_LIST);

        ResponseEntity<ResponseDto> sut = userController.getStudentLessons(MOCK_DOCUMENT_1);

        verify(userService, times(1)).getStudentLessons(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(GET_STUDENT_LESSONS_RESPONSE);
    }

    @Test
    void getStudentLessons_withUsersNotFound_returnsNotFound() {
        when(userService.getStudentLessons(MOCK_DOCUMENT_1)).thenThrow(new RuntimeException(LESSONS_NOT_FOUND));

        ResponseEntity<ResponseDto> sut = userController.getStudentLessons(MOCK_DOCUMENT_1);

        verify(userService, times(1)).getStudentLessons(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(NOT_FOUND_LESSONS_RESPONSE);
    }

    @Test
    public void getUserByEmail_withValidData_returnsUser() {
        when(userService.getUserByEmail(GET_USER_DTO_1.getEmail())).thenReturn(GET_USER_DTO_1);

        ResponseEntity<ResponseDto> sut = userController.getUserByEmail(GET_USER_DTO_1.getEmail());

        verify(userService, times(1)).getUserByEmail(GET_USER_DTO_1.getEmail());
        assertThat(sut).isEqualTo(GET_USER_BY_EMAIL_RESPONSE);
    }

    @Test
    void getUserByEmail_withUserNotFound_returnsNotFound() {
        when(userService.getUserByEmail(GET_USER_DTO_1.getEmail())).thenThrow(new RuntimeException(USER_NOT_FOUND));

        ResponseEntity<ResponseDto> sut = userController.getUserByEmail(GET_USER_DTO_1.getEmail());

        verify(userService, times(1)).getUserByEmail(GET_USER_DTO_1.getEmail());
        assertThat(sut).isEqualTo(NOT_FOUND_USER_RESPONSE);
    }
}