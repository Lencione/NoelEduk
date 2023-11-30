package br.com.noeleduk.noelproject.commons;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.LoggedUserDto;
import br.com.noeleduk.noelproject.dto.user.LoginRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class UserConstraints {

    // Messages
    public static final String USER_FOUND = "User found successfully";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVALID_PASSWORD = "Invalid password";
    public static final String INVALID_EMAIL = "Email invalido (prof.unieduk.com.br ou al.unieduk.com.br)";
    public static final String USER_CREATED = "User created with success!";
    public static final String DUPLICATED_EMAIL = "O E-mail ja esta em uso!";
    public static final String DUPLICATED_RA = "O RA ja esta em uso";
    public static final String DUPLICATED_CPF = "O CPF ja esta em uso";
    public static final String DUPLICATED_RG = "O RG ja esta em uso";
    public static final String STUDENT_EMAIL = "al.unieduk.com.br";
    public static final String TEACHER_EMAIL = "prof.unieduk.com.br";
    public static final String STUDENT_ROLE = "student";
    public static final String TEACHER_ROLE = "teacher";

    // DTOs
    public static final LoginRequestDto LOGIN_REQUEST_DTO = new LoginRequestDto("email@al.unieduk.com.br", "12345678");
    public static final LoginRequestDto INVALID_LOGIN_REQUEST_DTO = new LoginRequestDto("email@al.unieduk.com.br", "12345678");
    public static final LoginRequestDto NOT_FOUND_LOGIN_REQUEST_DTO = new LoginRequestDto("not_email@al.unieduk.com.br", "87654321");
    public static final LoggedUserDto LOGGED_USER_DTO = new LoggedUserDto("a45f2350-88ce-11ee-b9d1-0242ac120002", LocalDateTime.now(), "student", "12224577", "Ciência Da Computação");
    public static final CreateUserDto CREATE_USER_DTO = new CreateUserDto("pix.silva@al.unieduk.com.br", "12345678", "53923153815", "641075586", "Pix da Silva", "19996328763", "12224577","https://gravatar.com/avatar/dd95411d45214301e2673e1c6a8f5d47?s=400&d=robohash&r=x", "Ciência Da Computação");

    // Responses
    public static final ResponseEntity<ResponseDto> LOGIN_USER_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_FOUND, true, LOGGED_USER_DTO));
    public static final ResponseEntity<ResponseDto> NOT_FOUND_LOGIN_USER_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(USER_NOT_FOUND, false, null));
    public static final ResponseEntity<ResponseDto> INVALID_LOGIN_USER_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(INVALID_PASSWORD, false, null));
    public static final ResponseEntity<ResponseDto> CREATE_USER_RESPONSE = ResponseEntity.ok().body(new ResponseDto(USER_CREATED, true, LOGGED_USER_DTO));
    public static final ResponseEntity<ResponseDto> DUPLICATED_EMAIL_CREATE_USER_RESPONSE = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(DUPLICATED_EMAIL, false, null));
}
