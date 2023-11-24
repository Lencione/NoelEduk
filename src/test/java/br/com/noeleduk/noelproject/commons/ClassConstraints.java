package br.com.noeleduk.noelproject.commons;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.classes.CreateClassDto;
import br.com.noeleduk.noelproject.dto.classes.GetClassDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.subjects.GetSubjectDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ClassConstraints {

    // Constants
    public static final UUID MOCK_UUID = UUID.fromString("1376e95c-89a2-11ee-b9d1-0242ac120002");
    public static final String MOCK_DOCUMENT = "122245780";

    // Messages
    public static final String CLASS_CREATED = "Created class successfully";
    public static final String CLASSES_FOUND = "Classes found successfully";
    public static final String USER_ADDED_TO_CLASS = "User added to class successfully";
    public static final String STUDENT_ADDED_TO_CLASS = "Student added to class with success";
    public static final String STUDENTS_FOUND = "Students found successfully";
    public static final String TEACHERS_FOUND = "Teachers found";
    public static final String SUBJECTS_FOUND = "Subjects found successfully";
    public static final String LESSONS_FOUND = "Lessons found successfully";
    public static final String USER_FOUND = "User found";
    public static final String SUBJECT_CREATED = "Subject created successfully";
    public static final String TOKEN_CREATED = "Token created successfully";
    public static final String CLASS_ADDED_TO_SUBJECT = "Class added to subject successfully";
    public static final String INVALID_TEACHER_DOCUMENT = "Invalid teacher document";

    // DTOs
    public static final CreateClassDto CREATE_CLASS_DTO = new CreateClassDto("Banco de Dados 2", 4);
    public static final GetClassDto GET_CLASS_DTO_1 = new GetClassDto(MOCK_UUID, "Banco de Dados 2", 4);
    public static final GetClassDto GET_CLASS_DTO_2 = new GetClassDto(MOCK_UUID, "Estrutura de Dados", 6);
    public static final GetUserDto GET_STUDENT_DTO_1 = new GetUserDto("José Mozart", "jose.mozart789@al.unieduk.com.br", "1224875", "564", "https://gravatar.com/avatar/dd95411d45214301e2673e1c6a8f5d47?s=400&d=robohash&r=x", "112");
    public static final GetUserDto GET_STUDENT_DTO_2 = new GetUserDto("Luis Henrique", "luis.henrique789@al.unieduk.com.br", "1224175", "1034", "https://gravatar.com/avatar/dd95411d45214301e2673e1c6a8f5d47?s=400&d=robohash&r=x", "322");
    public static final GetSubjectDto GET_SUBJECT_DTO_1 = new GetSubjectDto(MOCK_UUID, "abc", "description 1", "Nadir Figueiredo", "12224578");
    public static final GetSubjectDto GET_SUBJECT_DTO_2 = new GetSubjectDto(MOCK_UUID, "def", "description 2", "Claiton Budini", "1112345");
    public static final AddStudentToClassDto ADD_STUDENT_TO_CLASS_DTO = new AddStudentToClassDto(MOCK_DOCUMENT);

    // Lists
    public static final List<GetClassDto> GET_CLASS_DTO_LIST = Arrays.asList(GET_CLASS_DTO_1, GET_CLASS_DTO_2);
    public static final List<GetUserDto> GET_STUDENT_DTO_LIST = Arrays.asList(GET_STUDENT_DTO_1, GET_STUDENT_DTO_2);
    public static final List<GetSubjectDto> GET_SUBJECT_DTO_LIST = Arrays.asList(GET_SUBJECT_DTO_1, GET_SUBJECT_DTO_2);

    // Responses
    public static final ResponseEntity<ResponseDto> CREATE_CLASS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(CLASS_CREATED, true, CLASS_CREATED));
    public static final ResponseEntity<ResponseDto> INVALID_CREATE_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> GET_CLASSES_RESPONSE = ResponseEntity.ok().body(new ResponseDto(CLASSES_FOUND, true, GET_CLASS_DTO_LIST));
    public static final ResponseEntity<ResponseDto> GET_SUBJECTS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(SUBJECTS_FOUND, true, GET_SUBJECT_DTO_LIST));
    public static final ResponseEntity<ResponseDto> GET_STUDENTS_BY_CLASS_ID_RESPONSE = ResponseEntity.ok().body(new ResponseDto(STUDENTS_FOUND, true, GET_STUDENT_DTO_LIST));
    public static final ResponseEntity<ResponseDto> INVALID_GET_CLASSES_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_GET_STUDENTS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_GET_SUBJECTS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_GET_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
    public static final ResponseEntity<ResponseDto> ADD_STUDENT_TO_CLASS_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_ADDED_TO_CLASS, true, STUDENT_ADDED_TO_CLASS));
    public static final ResponseEntity<ResponseDto> INVALID_ADD_STUDENT_TO_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null));
}
