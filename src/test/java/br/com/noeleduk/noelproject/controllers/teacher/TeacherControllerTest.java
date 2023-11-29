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
        when(teacherService.createClass(MOCK_DOCUMENT_1, CREATE_CLASS_DTO)).thenReturn(CLASS_CREATED);

        ResponseEntity<ResponseDto> sut = teacherController.createClass(MOCK_DOCUMENT_1, CREATE_CLASS_DTO);

        verify(teacherService, times(1)).createClass(MOCK_DOCUMENT_1, CREATE_CLASS_DTO);
        assertThat(sut).isEqualTo(CREATE_CLASS_RESPONSE);
    }

    @Test
    void createClass_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.createClass(MOCK_DOCUMENT_1, CREATE_CLASS_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.createClass(MOCK_DOCUMENT_1, CREATE_CLASS_DTO);

        verify(teacherService, times(1)).createClass(MOCK_DOCUMENT_1, CREATE_CLASS_DTO);
        assertThat(sut).isEqualTo(INVALID_CREATE_CLASS_RESPONSE);
    }

    @Test
    public void getClasses_withValidTeacherDocument_returnsClasses() {
        when(teacherService.getClassesByTeacherDocument(MOCK_DOCUMENT_1)).thenReturn(GET_CLASS_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getClasses(MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getClassesByTeacherDocument(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(GET_CLASSES_RESPONSE);
    }

    @Test
    void getClasses_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getClassesByTeacherDocument(MOCK_DOCUMENT_1)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getClasses(MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getClassesByTeacherDocument(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(INVALID_GET_CLASSES_RESPONSE);
    }

    @Test
    public void addStudentToClass_withValidData_returnsAddedUser() {
        when(teacherService.addStudentToClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_STUDENT_TO_CLASS_DTO)).thenReturn(STUDENT_ADDED_TO_CLASS);

        ResponseEntity<ResponseDto> sut = teacherController.addStudentToClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_STUDENT_TO_CLASS_DTO);

        verify(teacherService, times(1)).addStudentToClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_STUDENT_TO_CLASS_DTO);
        assertThat(sut).isEqualTo(ADD_STUDENT_TO_CLASS_RESPONSE);
    }

    @Test
    void addStudentToClass_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.addStudentToClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_STUDENT_TO_CLASS_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.addStudentToClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_STUDENT_TO_CLASS_DTO);

        verify(teacherService, times(1)).addStudentToClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_STUDENT_TO_CLASS_DTO);
        assertThat(sut).isEqualTo(INVALID_ADD_STUDENT_TO_CLASS_RESPONSE);
    }

    @Test
    public void getStudentsByClassId_withValidData_returnsClass() {
        when(teacherService.getStudentsByClassId(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenReturn(GET_STUDENT_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getStudentsByClassId(MOCK_UUID_1, MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getStudentsByClassId(MOCK_DOCUMENT_1, MOCK_UUID_1);
        assertThat(sut).isEqualTo(GET_STUDENTS_BY_CLASS_ID_RESPONSE);
    }

    @Test
    void getStudentsByClassId_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getStudentsByClassId(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getStudentsByClassId(MOCK_UUID_1, MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getStudentsByClassId(MOCK_DOCUMENT_1, MOCK_UUID_1);
        assertThat(sut).isEqualTo(INVALID_GET_STUDENTS_RESPONSE);
    }

    @Test
    public void getSubjects_withValidTeacherDocument_returnsClasses() {
        when(teacherService.getSubjectsByTeacherDocument(MOCK_DOCUMENT_1)).thenReturn(GET_SUBJECT_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getSubjects(MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getSubjectsByTeacherDocument(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(GET_SUBJECTS_RESPONSE);
    }

    @Test
    void getSubjects_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getSubjectsByTeacherDocument(MOCK_DOCUMENT_1)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getSubjects(MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getSubjectsByTeacherDocument(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(INVALID_GET_SUBJECTS_RESPONSE);
    }

    @Test
    public void createSubjects_withValidData_returnsClasses() {
        when(teacherService.createSubject(MOCK_DOCUMENT_1, CREATE_SUBJECT_DTO)).thenReturn(GET_SUBJECT_DTO_1);

        ResponseEntity<ResponseDto> sut = teacherController.createSubject(MOCK_DOCUMENT_1, CREATE_SUBJECT_DTO);

        verify(teacherService, times(1)).createSubject(MOCK_DOCUMENT_1, CREATE_SUBJECT_DTO);
        assertThat(sut).isEqualTo(GET_SUBJECT_RESPONSE);
    }

    @Test
    void createSubjects_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.createSubject(MOCK_DOCUMENT_1, CREATE_SUBJECT_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.createSubject(MOCK_DOCUMENT_1, CREATE_SUBJECT_DTO);

        verify(teacherService, times(1)).createSubject(MOCK_DOCUMENT_1, CREATE_SUBJECT_DTO);
        assertThat(sut).isEqualTo(INVALID_CREATE_SUBJECT_RESPONSE);
    }

    @Test
    public void addClass_withValidData_returnsClasses() {
        when(teacherService.addClassToSubject(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO)).thenReturn(ADD_CLASS_TO_SUBJECT_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.addClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO);

        verify(teacherService, times(1)).addClassToSubject(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO);
        assertThat(sut).isEqualTo(CLASS_ADDED_TO_SUBJECT_RESPONSE);
    }

    @Test
    void addClass_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.addClassToSubject(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.addClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO);

        verify(teacherService, times(1)).addClassToSubject(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO);
        assertThat(sut).isEqualTo(INVALID_CREATE_SUBJECT_RESPONSE);
    }

    @Test
    public void getStudentsBySubjectId_withValidData_returnsClasses() {
        when(teacherService.getStudentsBySubjectId(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenReturn(GET_STUDENT_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getStudentsBySubjectId(MOCK_UUID_1, MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getStudentsBySubjectId(MOCK_DOCUMENT_1, MOCK_UUID_1);
        assertThat(sut).isEqualTo(GET_STUDENTS_BY_SUBJECT_ID_RESPONSE);
    }

    @Test
    void getStudentsBySubjectId_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.addClassToSubject(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.addClass(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO);

        verify(teacherService, times(1)).addClassToSubject(MOCK_DOCUMENT_1, MOCK_UUID_1, ADD_CLASS_TO_SUBJECT_DTO);
        assertThat(sut).isEqualTo(INVALID_STUDENTS_BY_SUBJECT_ID_RESPONSE);
    }

    @Test
    public void getLessonsByTeacherDocument_withValidData_returnsClasses() {
        when(teacherService.getLessonsByTeacherDocument(MOCK_DOCUMENT_1)).thenReturn(GET_FORMATTED_LESSONS_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getLessonsByTeacherDocument(MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getLessonsByTeacherDocument(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(GET_LESSONS_BY_TEACHER_DOCUMENT_RESPONSE);
    }

    @Test
    void getLessonsByTeacherDocument_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.getLessonsByTeacherDocument(MOCK_DOCUMENT_1)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.getLessonsByTeacherDocument(MOCK_DOCUMENT_1);

        verify(teacherService, times(1)).getLessonsByTeacherDocument(MOCK_DOCUMENT_1);
        assertThat(sut).isEqualTo(INVALID_GET_LESSONS_BY_TEACHER_DOCUMENT_RESPONSE);
    }
}
