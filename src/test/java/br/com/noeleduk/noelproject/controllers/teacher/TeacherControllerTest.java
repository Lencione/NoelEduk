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
    public void createClass_withValidData_returnsCreatedClass() {
        when(teacherService.createClass(MOCK_DOCUMENT, CREATE_CLASS_DTO)).thenReturn(CLASS_CREATED);

        ResponseEntity<ResponseDto> sut = teacherController.createClass(MOCK_DOCUMENT, CREATE_CLASS_DTO);

        verify(teacherService, times(1)).createClass(MOCK_DOCUMENT, CREATE_CLASS_DTO);
        assertThat(sut).isEqualTo(CREATE_CLASS_RESPONSE);
    }

    @Test
    void createClass_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.createClass(MOCK_DOCUMENT, CREATE_CLASS_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.createClass(MOCK_DOCUMENT, CREATE_CLASS_DTO);

        verify(teacherService, times(1)).createClass(MOCK_DOCUMENT, CREATE_CLASS_DTO);
        assertThat(sut).isEqualTo(INVALID_CREATE_CLASS_RESPONSE);
    }

    @Test
    public void getClasses_withValidTeacherDocument_returnsClasses() {
        when(teacherService.getClassesByTeacherDocument(MOCK_DOCUMENT)).thenReturn(GET_CLASS_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getClasses(MOCK_DOCUMENT);

        verify(teacherService, times(1)).getClassesByTeacherDocument(MOCK_DOCUMENT);
        assertThat(sut).isEqualTo(GET_CLASSES_RESPONSE);
    }

    @Test
    void getClasses_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getClassesByTeacherDocument(MOCK_DOCUMENT)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getClasses(MOCK_DOCUMENT);

        verify(teacherService, times(1)).getClassesByTeacherDocument(MOCK_DOCUMENT);
        assertThat(sut).isEqualTo(INVALID_GET_CLASSES_RESPONSE);
    }

    @Test
    public void addStudentToClass_withValidData_returnsAddedUser() {
        when(teacherService.addStudentToClass(MOCK_DOCUMENT, MOCK_UUID, ADD_STUDENT_TO_CLASS_DTO)).thenReturn(STUDENT_ADDED_TO_CLASS);

        ResponseEntity<ResponseDto> sut = teacherController.addStudentToClass(MOCK_DOCUMENT, MOCK_UUID, ADD_STUDENT_TO_CLASS_DTO);

        verify(teacherService, times(1)).addStudentToClass(MOCK_DOCUMENT, MOCK_UUID, ADD_STUDENT_TO_CLASS_DTO);
        assertThat(sut).isEqualTo(ADD_STUDENT_TO_CLASS_RESPONSE);
    }

    @Test
    void addStudentToClass_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.addStudentToClass(MOCK_DOCUMENT, MOCK_UUID, ADD_STUDENT_TO_CLASS_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.addStudentToClass(MOCK_DOCUMENT, MOCK_UUID, ADD_STUDENT_TO_CLASS_DTO);

        verify(teacherService, times(1)).addStudentToClass(MOCK_DOCUMENT, MOCK_UUID, ADD_STUDENT_TO_CLASS_DTO);
        assertThat(sut).isEqualTo(INVALID_ADD_STUDENT_TO_CLASS_RESPONSE);
    }

    @Test
    public void getStudentsByClassId_withValidData_returnsClass() {
        when(teacherService.getStudentsByClassId(MOCK_DOCUMENT, MOCK_UUID)).thenReturn(GET_STUDENT_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getStudentsByClassId(MOCK_UUID, MOCK_DOCUMENT);

        verify(teacherService, times(1)).getStudentsByClassId(MOCK_DOCUMENT, MOCK_UUID);
        assertThat(sut).isEqualTo(GET_STUDENTS_BY_CLASS_ID_RESPONSE);
    }

    @Test
    void getStudentsByClassId_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getStudentsByClassId(MOCK_DOCUMENT, MOCK_UUID)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getStudentsByClassId(MOCK_UUID, MOCK_DOCUMENT);

        verify(teacherService, times(1)).getStudentsByClassId(MOCK_DOCUMENT, MOCK_UUID);
        assertThat(sut).isEqualTo(INVALID_GET_STUDENTS_RESPONSE);
    }
}
