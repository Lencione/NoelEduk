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
    public void getStudentsByClassId_withValidData_returnsStudentList() {
        when(teacherService.getStudentsByClassId(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenReturn(GET_USER_DTO_LIST);

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
    public void getSubjects_withValidTeacherDocument_returnsSubjects() {
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
    public void createSubjects_withValidData_returnsSubject() {
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
    public void addClass_withValidData_returnsClassList() {
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
    public void getStudentsBySubjectId_withValidData_returnsStudent() {
        when(teacherService.getStudentsBySubjectId(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenReturn(GET_USER_DTO_LIST);

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
    public void getLessonsByTeacherDocument_withValidData_returnsLessonsList() {
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

    @Test
    public void createLessonToken_withValidData_returnsToken() {
        when(teacherService.createLessonToken(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenReturn(MOCK_TOKEN_1);

        ResponseEntity<ResponseDto> sut = teacherController.createLessonToken(MOCK_DOCUMENT_1, MOCK_UUID_1);

        verify(teacherService, times(1)).createLessonToken(MOCK_DOCUMENT_1, MOCK_UUID_1);
        assertThat(sut).isEqualTo(CREATE_LESSON_TOKEN_RESPONSE);
    }

    @Test
    void createLessonToken_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.createLessonToken(MOCK_DOCUMENT_1, MOCK_UUID_1)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.createLessonToken(MOCK_DOCUMENT_1, MOCK_UUID_1);

        verify(teacherService, times(1)).createLessonToken(MOCK_DOCUMENT_1, MOCK_UUID_1);
        assertThat(sut).isEqualTo(INVALID_CREATE_LESSON_TOKEN_RESPONSE);
    }

    @Test
    public void markPresences_withValidData_returnsPresenceList() {
        when(teacherService.markPresenceToStudent(MOCK_DOCUMENT_1, MOCK_UUID_1, MARK_LESSON_STUDENT_PRESENCE_DTO)).thenReturn(MARK_USER_PRESENCE_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.markPresences(MOCK_DOCUMENT_1, MOCK_UUID_1, MARK_LESSON_STUDENT_PRESENCE_DTO);

        verify(teacherService, times(1)).markPresenceToStudent(MOCK_DOCUMENT_1, MOCK_UUID_1, MARK_LESSON_STUDENT_PRESENCE_DTO);
        assertThat(sut).isEqualTo(MARK_LESSON_STUDENT_PRESENCE_DTO_RESPONSE);
    }

    @Test
    void markPresences_withInvalidTeacherDocument_returnsInvalidTeacher() {
        when(teacherService.markPresenceToStudent(MOCK_DOCUMENT_1, MOCK_UUID_1, MARK_LESSON_STUDENT_PRESENCE_DTO)).thenThrow(new RuntimeException(INVALID_TEACHER_DOCUMENT));

        ResponseEntity<ResponseDto> sut = teacherController.markPresences(MOCK_DOCUMENT_1, MOCK_UUID_1, MARK_LESSON_STUDENT_PRESENCE_DTO);

        verify(teacherService, times(1)).markPresenceToStudent(MOCK_DOCUMENT_1, MOCK_UUID_1, MARK_LESSON_STUDENT_PRESENCE_DTO);
        assertThat(sut).isEqualTo(INVALID_MARK_LESSON_STUDENT_PRESENCE_DTO_RESPONSE);
    }

    @Test
    public void getAllTeachers_withValidData_returnsTeachers() {
        when(teacherService.getAllTeachers()).thenReturn(GET_USER_DTO_LIST);

        ResponseEntity<ResponseDto> sut = teacherController.getAllTeachers();

        verify(teacherService, times(1)).getAllTeachers();
        assertThat(sut).isEqualTo(GET_ALL_TEACHERS_RESPONSE);
    }

    @Test
    void getAllTeachers_withTeachersNotFound_returnsNotFound() {
        when(teacherService.getAllTeachers()).thenThrow(new RuntimeException(TEACHERS_NOT_FOUND));

        ResponseEntity<ResponseDto> sut = teacherController.getAllTeachers();

        verify(teacherService, times(1)).getAllTeachers();
        assertThat(sut).isEqualTo(NOT_FOUND_TEACHERS_RESPONSE);
    }

    @Test
    public void getUserByEmail_withValidData_returnsUser() {
        when(teacherService.getTeacherByEmail(GET_USER_DTO_1.getEmail())).thenReturn(GET_USER_DTO_1);

        ResponseEntity<ResponseDto> sut = teacherController.getUserByEmail(GET_USER_DTO_1.getEmail());

        verify(teacherService, times(1)).getTeacherByEmail(GET_USER_DTO_1.getEmail());
        assertThat(sut).isEqualTo(GET_TEACHER_BY_EMAIL_RESPONSE);
    }

    @Test
    void getUserByEmail_withUserNotFound_returnsNotFound() {
        when(teacherService.getTeacherByEmail(GET_USER_DTO_1.getEmail())).thenThrow(new RuntimeException(TEACHER_NOT_FOUND));

        ResponseEntity<ResponseDto> sut = teacherController.getUserByEmail(GET_USER_DTO_1.getEmail());

        verify(teacherService, times(1)).getTeacherByEmail(GET_USER_DTO_1.getEmail());
        assertThat(sut).isEqualTo(NOT_FOUND_TEACHER_RESPONSE);
    }

    @Test
    public void getUserByDocument_withValidData_returnsUser() {
        when(teacherService.getTeacherByDocument(GET_USER_DTO_1.getDocument())).thenReturn(GET_USER_DTO_1);

        ResponseEntity<ResponseDto> sut = teacherController.getUserByDocument(GET_USER_DTO_1.getDocument());

        verify(teacherService, times(1)).getTeacherByDocument(GET_USER_DTO_1.getDocument());
        assertThat(sut).isEqualTo(GET_TEACHER_BY_DOCUMENT_RESPONSE);
    }

    @Test
    void getUserByDocument_withUserNotFound_returnsNotFound() {
        when(teacherService.getTeacherByDocument(GET_USER_DTO_1.getDocument())).thenThrow(new RuntimeException(TEACHER_NOT_FOUND));

        ResponseEntity<ResponseDto> sut = teacherController.getUserByDocument(GET_USER_DTO_1.getDocument());

        verify(teacherService, times(1)).getTeacherByDocument(GET_USER_DTO_1.getDocument());
        assertThat(sut).isEqualTo(NOT_FOUND_TEACHER_RESPONSE);
    }
}
