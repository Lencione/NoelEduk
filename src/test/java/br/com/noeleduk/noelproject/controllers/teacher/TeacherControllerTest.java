package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.services.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static br.com.noeleduk.noelproject.commons.ClassConstraints.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {
    @InjectMocks
    private TeacherController teacherController;

    @Mock
    private TeacherService teacherService;

    @Test
    public void createClass_withValidTeacherDocument_returnsCreatedClass() {
        when(teacherService.createClass(TEACHER_DOCUMENT, CREATE_CLASS_DTO)).thenReturn(CLASS_CREATED);

        ResponseEntity<ResponseDto> sut = teacherController.createClass(TEACHER_DOCUMENT, CREATE_CLASS_DTO);

        verify(teacherService, times(1)).createClass(TEACHER_DOCUMENT, CREATE_CLASS_DTO);
        assertThat(sut).isEqualTo(CREATE_CLASS_RESPONSE);
    }

    @Test
    void createClass_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.createClass(TEACHER_DOCUMENT, CREATE_CLASS_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.createClass(TEACHER_DOCUMENT, CREATE_CLASS_DTO);

        verify(teacherService, times(1)).createClass(TEACHER_DOCUMENT, CREATE_CLASS_DTO);
        assertThat(sut).isEqualTo(INVALID_CREATE_CLASS_RESPONSE);
    }

    @Test
    public void getClasses_withValidTeacherDocument_returnsClasses() {
        when(teacherService.getClassesByTeacherDocument(TEACHER_DOCUMENT)).thenReturn(GET_CLASS_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getClasses(TEACHER_DOCUMENT);

        verify(teacherService, times(1)).getClassesByTeacherDocument(TEACHER_DOCUMENT);
        assertThat(sut).isEqualTo(GET_CLASSES_RESPONSE);
    }

    @Test
    void getClasses_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getClassesByTeacherDocument(TEACHER_DOCUMENT)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getClasses(TEACHER_DOCUMENT);

        verify(teacherService, times(1)).getClassesByTeacherDocument(TEACHER_DOCUMENT);
        assertThat(sut).isEqualTo(INVALID_GET_CLASSES_RESPONSE);
    }
}
