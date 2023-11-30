package br.com.noeleduk.noelproject.commons;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.classes.CreateClassDto;
import br.com.noeleduk.noelproject.dto.classes.GetClassDto;
import br.com.noeleduk.noelproject.dto.lessons.GetFormattedLessonsDto;
import br.com.noeleduk.noelproject.dto.lessons.GetLessonDto;
import br.com.noeleduk.noelproject.dto.lessons.GetUserLessonsDto;
import br.com.noeleduk.noelproject.dto.lessons.MarkLessonStudentPresenceDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.GetSubjectDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.MarkUserPresenceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static br.com.noeleduk.noelproject.commons.UserConstraints.USER_NOT_FOUND;

public class ClassConstraints {

    // Constants
    public static final UUID MOCK_UUID_1 = UUID.fromString("1376e95c-89a2-11ee-b9d1-0242ac120002");
    public static final UUID MOCK_UUID_2 = UUID.fromString("54d66398-8e41-11ee-b9d1-0242ac120002");
    public static final String MOCK_TOKEN_1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    public static final String MOCK_TOKEN_2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikpvc2ggRHVuIiwiaWF0IjoxNTE2MjM5MDIyfQ.oFgGSDGv5lacmL8tlc-MVB7CBgPoLHpWROzOtG9ho1Q";

    public static final String MOCK_DOCUMENT_1 = "122245780";
    public static final String MOCK_DOCUMENT_2 = "133358120";
    public static final List<String> MOCK_DOCUMENT_LIST = Arrays.asList(MOCK_DOCUMENT_1, MOCK_DOCUMENT_2);



    // Messages
    public static final String CLASS_CREATED = "Created class successfully";
    public static final String CLASSES_FOUND = "Classes found successfully";
    public static final String USER_ADDED_TO_CLASS = "User added to class successfully";
    public static final String STUDENT_ADDED_TO_CLASS = "Student added to class with success";
    public static final String STUDENTS_FOUND = "Students found successfully";
    public static final String TEACHERS_FOUND = "Teachers found";
    public static final String USERS_FOUND = "Users found successfully";
    public static final String TEACHER_NOT_FOUND = "Teacher not found";
    public static final String TEACHERS_NOT_FOUND = "Teachers not found";
    public static final String USERS_NOT_FOUND = "Users not found";
    public static final String SUBJECTS_FOUND = "Subjects found successfully";
    public static final String LESSONS_FOUND = "Lessons found successfully";
    public static final String LESSONS_NOT_FOUND = "Lessons not found";
    public static final String USER_FOUND = "User found successfully";
    public static final String SUBJECT_CREATED = "Subject created successfully";
    public static final String TOKEN_CREATED = "Token created successfully";
    public static final String CLASS_ADDED_TO_SUBJECT = "Class added to subject successfully";
    public static final String INVALID_TEACHER_DOCUMENT = "Invalid teacher document";
    public static final String PRESENCES_MARKED = "Presences marked successfully";

    // DTOs
    public static final CreateClassDto CREATE_CLASS_DTO = new CreateClassDto("Banco de Dados 2", 4);
    public static final GetClassDto GET_CLASS_DTO_1 = new GetClassDto(MOCK_UUID_1, "Banco de Dados 2", 4);
    public static final GetClassDto GET_CLASS_DTO_2 = new GetClassDto(MOCK_UUID_1, "Estrutura de Dados", 6);
    public static final GetUserDto GET_USER_DTO_1 = new GetUserDto("José Mozart", "jose.mozart789@al.unieduk.com.br", "1224875", "564", "https://gravatar.com/avatar/dd95411d45214301e2673e1c6a8f5d47?s=400&d=robohash&r=x", "112", "Ciência Da Computação");
    public static final GetUserDto GET_USER_DTO_2 = new GetUserDto("Luis Henrique", "luis.henrique789@al.unieduk.com.br", "1224175", "1034", "https://gravatar.com/avatar/dd95411d45214301e2673e1c6a8f5d47?s=400&d=robohash&r=x", "322", "Ciência Da Computação");
    public static final GetSubjectDto GET_SUBJECT_DTO_1 = new GetSubjectDto(MOCK_UUID_1, "abc", "description 1", "Nadir Figueiredo", "12224578", "Bsx5s84", 2);
    public static final GetSubjectDto GET_SUBJECT_DTO_2 = new GetSubjectDto(MOCK_UUID_1, "def", "description 2", "Claiton Budini", "1112345", "Sfc8f13", 3);
    public static final AddStudentToClassDto ADD_STUDENT_TO_CLASS_DTO = new AddStudentToClassDto(MOCK_DOCUMENT_LIST);
    public static final CreateSubjectDto CREATE_SUBJECT_DTO = new CreateSubjectDto("Banco De Dados 3", "Vs21de", 3, LocalDate.now(), LocalDate.now());
    public static final AddClassToSubjectDto ADD_CLASS_TO_SUBJECT_DTO = new AddClassToSubjectDto(Arrays.asList(MOCK_UUID_1, MOCK_UUID_2));
    public static final GetLessonDto GET_LESSON_DTO_1 = new GetLessonDto(MOCK_UUID_1, LocalDate.now(), "Conceitos avançados de JDBC", "123456","Programação Orientada a Objetos", "Marco Miquelino", "Justificação", true, true, 12);
    public static final GetLessonDto GET_LESSON_DTO_2 = new GetLessonDto(MOCK_UUID_1, LocalDate.now(), "Conceitos avançados de SQL", "654321","Banco de Dados 3", "Luciano Calderoni", "Justificação", true, true, 12);
    public static final GetLessonDto GET_LESSON_DTO_3 = new GetLessonDto(MOCK_UUID_1, LocalDate.now(), "Conceitos avançados de LISTA", "123654","Estrutura de Dados", "Carlos Miglinks", "Justificação", true, true, 8);
    public static final GetLessonDto GET_LESSON_DTO_4 = new GetLessonDto(MOCK_UUID_1, LocalDate.now(), "Conceitos avançados de REDES", "654123","Redes Móveis", "Nadir Dias", "Justificação", true, true, 8);
    public static final GetUserLessonsDto GET_USER_LESSONS_DTO_1 = new GetUserLessonsDto(LocalDate.now(), "123456", "Conceitos avançados de JDBC", "Marco Miquelino","Programação Orientada a Objetos", "Justificação", true, true, true, 12);
    public static final GetUserLessonsDto GET_USER_LESSONS_DTO_2 = new GetUserLessonsDto(LocalDate.now(), "654321","Conceitos avançados de SQL", "Luciano Calderoni","Banco de Dados 3", "Justificação", true, true, true, 12);
    public static final GetUserLessonsDto GET_USER_LESSONS_DTO_3 = new GetUserLessonsDto(LocalDate.now(), "123654","Conceitos avançados de LISTA", "Carlos Miglinks","Estrutura de Dados", "Justificação", true, true, true, 8);
    public static final GetUserLessonsDto GET_USER_LESSONS_DTO_4 = new GetUserLessonsDto(LocalDate.now(), "654123","Conceitos avançados de REDES", "Nadir Dias","Redes Móveis", "Justificação", true, true, true, 8);
    public static final MarkUserPresenceDto MARK_USER_PRESENCE_DTO = new MarkUserPresenceDto(MOCK_TOKEN_1);
    public static final MarkLessonStudentPresenceDto MARK_LESSON_STUDENT_PRESENCE_DTO = new MarkLessonStudentPresenceDto(Arrays.asList(MOCK_DOCUMENT_1, MOCK_DOCUMENT_2));

    // Lists
    public static final List<GetClassDto> GET_CLASS_DTO_LIST = Arrays.asList(GET_CLASS_DTO_1, GET_CLASS_DTO_2);
    public static final List<GetUserDto> GET_USER_DTO_LIST = Arrays.asList(GET_USER_DTO_1, GET_USER_DTO_2);
    public static final List<GetLessonDto> GET_LESSON_DTO_LIST = Arrays.asList(GET_LESSON_DTO_1, GET_LESSON_DTO_2);
    public static final List<GetSubjectDto> GET_SUBJECT_DTO_LIST = Arrays.asList(GET_SUBJECT_DTO_1, GET_SUBJECT_DTO_2);
    public static final List<String> ADD_CLASS_TO_SUBJECT_LIST = Arrays.asList("Banco de Dados added to subject", "Estrutura de Dados already in subject");
    public static final GetFormattedLessonsDto<GetLessonDto> GET_FORMATTED_LESSONS_DTO_1 = new GetFormattedLessonsDto<>(12, Arrays.asList(GET_LESSON_DTO_1, GET_LESSON_DTO_2));
    public static final GetFormattedLessonsDto<GetLessonDto> GET_FORMATTED_LESSONS_DTO_2 = new GetFormattedLessonsDto<>(8, Arrays.asList(GET_LESSON_DTO_3, GET_LESSON_DTO_4));
    public static final GetFormattedLessonsDto<GetUserLessonsDto> GET_FORMATTED_USER_LESSONS_DTO_1 = new GetFormattedLessonsDto<>(12, Arrays.asList(GET_USER_LESSONS_DTO_1, GET_USER_LESSONS_DTO_2));
    public static final GetFormattedLessonsDto<GetUserLessonsDto> GET_FORMATTED_USER_LESSONS_DTO_2 = new GetFormattedLessonsDto<>(8, Arrays.asList(GET_USER_LESSONS_DTO_3, GET_USER_LESSONS_DTO_4));
    public static final List<GetFormattedLessonsDto<GetLessonDto>> GET_FORMATTED_LESSONS_DTO_LIST = Arrays.asList(GET_FORMATTED_LESSONS_DTO_1, GET_FORMATTED_LESSONS_DTO_2);
    public static final List<GetFormattedLessonsDto<GetUserLessonsDto>> GET_FORMATTED_USER_LESSONS_DTO_LIST = Arrays.asList(GET_FORMATTED_USER_LESSONS_DTO_1, GET_FORMATTED_USER_LESSONS_DTO_2);
    public static final List<String> MARK_USER_PRESENCE_LIST = Arrays.asList("Rafael Menegon is already marked as present", "Glauber Silva marked as present");

    // Responses
    public static final ResponseEntity<ResponseDto> CREATE_CLASS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(CLASS_CREATED, true, CLASS_CREATED));
    public static final ResponseEntity<ResponseDto> INVALID_CREATE_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> GET_CLASSES_RESPONSE = ResponseEntity.ok().body(new ResponseDto(CLASSES_FOUND, true, GET_CLASS_DTO_LIST));
    public static final ResponseEntity<ResponseDto> GET_SUBJECTS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(SUBJECTS_FOUND, true, GET_SUBJECT_DTO_LIST));
    public static final ResponseEntity<ResponseDto> GET_SUBJECT_RESPONSE = ResponseEntity.ok().body(new ResponseDto(SUBJECT_CREATED, true, GET_SUBJECT_DTO_1));
    public static final ResponseEntity<ResponseDto> GET_STUDENTS_BY_CLASS_ID_RESPONSE = ResponseEntity.ok().body(new ResponseDto(STUDENTS_FOUND, true, GET_USER_DTO_LIST));
    public static final ResponseEntity<ResponseDto> INVALID_GET_CLASSES_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_GET_STUDENTS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_GET_SUBJECTS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_GET_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> ADD_STUDENT_TO_CLASS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_ADDED_TO_CLASS, true, STUDENT_ADDED_TO_CLASS));
    public static final ResponseEntity<ResponseDto> INVALID_ADD_STUDENT_TO_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> CREATE_SUBJECT_RESPONSE = ResponseEntity.ok().body(new ResponseDto(SUBJECT_CREATED, true, SUBJECT_CREATED));
    public static final ResponseEntity<ResponseDto> INVALID_CREATE_SUBJECT_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> CLASS_ADDED_TO_SUBJECT_RESPONSE = ResponseEntity.ok().body(new ResponseDto(CLASS_ADDED_TO_SUBJECT, true, ADD_CLASS_TO_SUBJECT_LIST));
    public static final ResponseEntity<ResponseDto> GET_STUDENTS_BY_SUBJECT_ID_RESPONSE = ResponseEntity.ok().body(new ResponseDto(STUDENTS_FOUND, true, GET_USER_DTO_LIST));
    public static final ResponseEntity<ResponseDto> INVALID_STUDENTS_BY_SUBJECT_ID_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> GET_LESSONS_BY_TEACHER_DOCUMENT_RESPONSE = ResponseEntity.ok().body(new ResponseDto(LESSONS_FOUND, true, GET_FORMATTED_LESSONS_DTO_LIST));
    public static final ResponseEntity<ResponseDto> INVALID_GET_LESSONS_BY_TEACHER_DOCUMENT_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> CREATE_LESSON_TOKEN_RESPONSE = ResponseEntity.ok().body(new ResponseDto(TOKEN_CREATED, true, MOCK_TOKEN_1));
    public static final ResponseEntity<ResponseDto> INVALID_CREATE_LESSON_TOKEN_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> MARK_LESSON_STUDENT_PRESENCE_DTO_RESPONSE = ResponseEntity.ok().body(new ResponseDto(PRESENCES_MARKED, true, MARK_USER_PRESENCE_LIST));

    public static final ResponseEntity<ResponseDto> INVALID_MARK_LESSON_STUDENT_PRESENCE_DTO_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> GET_ALL_TEACHERS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(TEACHERS_FOUND, true, GET_USER_DTO_LIST));
    public static final ResponseEntity<ResponseDto> GET_ALL_USERS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USERS_FOUND, true, GET_USER_DTO_LIST));
    public static final ResponseEntity<ResponseDto> NOT_FOUND_TEACHERS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(TEACHERS_NOT_FOUND, false, null));
    public static final ResponseEntity<ResponseDto> NOT_FOUND_USERS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(USERS_NOT_FOUND, false, null));
    public static final ResponseEntity<ResponseDto> NOT_FOUND_USER_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(USER_NOT_FOUND, false, null));
    public static final ResponseEntity<ResponseDto> GET_TEACHER_BY_EMAIL_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_FOUND, true, GET_USER_DTO_1));
    public static final ResponseEntity<ResponseDto> GET_USER_BY_EMAIL_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_FOUND, true, GET_USER_DTO_1));
    public static final ResponseEntity<ResponseDto> NOT_FOUND_TEACHER_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(TEACHER_NOT_FOUND, false, null));
    public static final ResponseEntity<ResponseDto> GET_TEACHER_BY_DOCUMENT_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_FOUND, true, GET_USER_DTO_1));
    public static final ResponseEntity<ResponseDto> GET_STUDENT_LESSONS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(LESSONS_FOUND, true, GET_FORMATTED_USER_LESSONS_DTO_LIST));
    public static final ResponseEntity<ResponseDto> NOT_FOUND_LESSONS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(LESSONS_NOT_FOUND, false, null));

}
