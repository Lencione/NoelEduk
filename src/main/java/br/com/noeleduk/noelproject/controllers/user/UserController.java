package br.com.noeleduk.noelproject.controllers.user;

import br.com.noeleduk.noelproject.dto.lessons.GetUserLessonsDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/")
  public ResponseEntity<ResponseDto> getAllUsers() {
    try {
      List<GetUserDto> userEntities = service.getAllUsers();
      return ResponseEntity.ok().body(
              new ResponseDto("Users found", true, userEntities)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
  @GetMapping("{document}/lessons")
  public ResponseEntity<ResponseDto> getStudentLessons(@PathVariable String document){
    try{
      List<GetUserLessonsDto> user = service.getStudentLessons(document);
      return ResponseEntity.ok().body(
              new ResponseDto("Lessons found", true, user)
      );
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
  @GetMapping("/{email}")
  public ResponseEntity<ResponseDto> getUserByEmail(@PathVariable String email) {
    try {
      GetUserDto user = service.getUserByEmail(email);
      return ResponseEntity.ok().body(
              new ResponseDto("User found", true, user)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
