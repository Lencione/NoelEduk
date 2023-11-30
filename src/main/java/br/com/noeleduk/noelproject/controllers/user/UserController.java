package br.com.noeleduk.noelproject.controllers.user;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.user.GetStudentCardDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserPresenceDto;
import br.com.noeleduk.noelproject.dto.user.MarkUserPresenceDto;
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
              new ResponseDto("Users found successfully", true, userEntities)
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
      return ResponseEntity.ok().body(
              new ResponseDto("Lessons found successfully", true, service.getStudentLessons(document))
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
              new ResponseDto("User found successfully", true, user)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("{user}/markPresence")
  public ResponseEntity<ResponseDto> markPresence(@PathVariable String user, @RequestBody MarkUserPresenceDto presence) {
    try {
      service.markPresence(user,presence);
      return ResponseEntity.ok().body(
              new ResponseDto("Presence marked", true, "Success")
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @GetMapping("/{user}/card")
  public ResponseEntity<ResponseDto> getStudentCard(@PathVariable String user) {
    try {
      GetStudentCardDto card = service.getStudentCard(user);
      return ResponseEntity.ok().body(
              new ResponseDto("Card found successfully", true, card)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @GetMapping("/{user}/presences")
  public ResponseEntity<ResponseDto> getUserPresences(@PathVariable String user) {
    try {
      List<GetUserPresenceDto> presences = service.getUserPresences(user);
      return ResponseEntity.ok().body(
              new ResponseDto("Presences found successfully", true, presences)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
