package br.com.noeleduk.noelproject.Controllers.User;

import br.com.noeleduk.noelproject.Dto.Response.ResponseDto;
import br.com.noeleduk.noelproject.Dto.User.GetUserDto;
import br.com.noeleduk.noelproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/getAll")
  public ResponseEntity<ResponseDto> getAllUsers() {
    try {
      List<GetUserDto> userEntities = userService.getAllUsers();
      return ResponseEntity.ok().body(
              new ResponseDto("Users found", true, userEntities)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @GetMapping("/{email}")
  public ResponseEntity<ResponseDto> getUserByEmail(@PathVariable String email) {
    try {
      GetUserDto user = userService.getUserByEmail(email);
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
