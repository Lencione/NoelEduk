package br.com.noeleduk.noelproject.commons;

import br.com.noeleduk.noelproject.dto.classes.CreateClassDto;
import br.com.noeleduk.noelproject.dto.classes.GetClassDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ClassConstraints {

    public static final String MOCK_UUID = "1376e95c-89a2-11ee-b9d1-0242ac120002";
    public static final String CLASS_CREATED = "Created class successfully";
    public static final String CLASSES_FOUND = "Classes found successfully";
    public static final String USER_ADDED_TO_CLASS = "User added to class successfully";
    public static final String STUDENTS_FOUND = "Students found successfully";
    public static final String TEACHERS_FOUND = "Teachers found";
    public static final String SUBJECTS_FOUND = "Subjects found successfully";
    public static final String LESSONS_FOUND = "Lessons found successfully";
    public static final String USER_FOUND = "User found";
    public static final String SUBJECT_CREATED = "Subject created successfully";
    public static final String TOKEN_CREATED = "Token created successfully";
    public static final String CLASS_ADDED_TO_SUBJECT = "Class added to subject successfully";
    public static final String INVALID_TEACHER_DOCUMENT = "Invalid teacher document";
    public static final CreateClassDto CREATE_CLASS_DTO = new CreateClassDto("Banco de Dados 2", 4);
    public static final String TEACHER_DOCUMENT = "12224579";
    public static final ResponseEntity<ResponseDto> CREATE_CLASS_RESPONSE = ResponseEntity.ok().body(
        new ResponseDto(CLASS_CREATED, true, CLASS_CREATED)
    );
    public static final ResponseEntity<ResponseDto> INVALID_CREATE_CLASS_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null)
    );

    public static final GetClassDto GET_CLASS_DTO_1 = new GetClassDto(UUID.fromString(MOCK_UUID), "Banco de Dados 2", 4);

    public static final GetClassDto GET_CLASS_DTO_2 = new GetClassDto(UUID.fromString(MOCK_UUID), "Estrutura de Dados", 6);

    public static final List<GetClassDto> GET_CLASS_DTO_LIST = Arrays.asList(
        GET_CLASS_DTO_1,
        GET_CLASS_DTO_2
    );

    public static final ResponseEntity<ResponseDto> GET_CLASSES_RESPONSE = ResponseEntity.ok().body(
            new ResponseDto(CLASSES_FOUND, true, GET_CLASS_DTO_LIST)
    );

    public static final ResponseEntity<ResponseDto> INVALID_GET_CLASSES_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseDto(INVALID_TEACHER_DOCUMENT, false, null)
    );

    
}
