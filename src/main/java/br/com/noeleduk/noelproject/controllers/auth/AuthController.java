package br.com.noeleduk.noelproject.controllers.auth;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.LoginRequestDto;
import br.com.noeleduk.noelproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private UserService service;

  @PostMapping("/login")
  public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto login) {
    try {

      return ResponseEntity.ok().body(
              new ResponseDto("User found successfully", true, service.login(login))
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
    try {
      return ResponseEntity.ok().body(
              new ResponseDto("User created with success!", true, service.create(createUserDto))
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
