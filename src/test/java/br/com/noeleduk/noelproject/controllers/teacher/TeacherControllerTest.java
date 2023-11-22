package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.classes.CreateClassDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.services.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateClass() {
        // Arrange
        CreateClassDto createClassDto = new CreateClassDto();
        ResponseEntity<ResponseDto> expectedResponse = ResponseEntity.ok(new ResponseDto("Created class successfully", true, null));
        when(teacherService.createClass(anyString(), any(CreateClassDto.class))).thenReturn(null);

        // Act
        ResponseEntity<ResponseDto> response = teacherController.createClass("document", createClassDto);

        // Assert
        verify(teacherService, times(1)).createClass(anyString(), any(CreateClassDto.class));
        // Add more assertions as needed based on your requirements
    }

    // Write similar tests for other controller methods...

    @Test
    void testGetAllTeachers() {
        // Arrange
        GetUserDto getUserDto = new GetUserDto();
        when(teacherService.getAllTeachers()).thenReturn(Collections.singletonList(getUserDto));

        // Act
        ResponseEntity<ResponseDto> response = teacherController.getAllTeachers();

        // Assert
        verify(teacherService, times(1)).getAllTeachers();
        // Add more assertions as needed based on your requirements
    }
}
