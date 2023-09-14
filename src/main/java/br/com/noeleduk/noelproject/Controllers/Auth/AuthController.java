package br.com.noeleduk.noelproject.Controllers.Auth;

import br.com.noeleduk.noelproject.Dto.Response.ResponseDto;
import br.com.noeleduk.noelproject.Dto.User.CreateUserDto;
import br.com.noeleduk.noelproject.Dto.User.GetUserDto;
import br.com.noeleduk.noelproject.Dto.User.LoginRequestDto;
import br.com.noeleduk.noelproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private UserService service;

  @PostMapping("/login")
  public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto user) {
    try {
      return ResponseEntity.ok().body(
              new ResponseDto("User found", true, service.login(user))
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
              new ResponseDto("User created with success!", true, service.createUser(createUserDto))
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
