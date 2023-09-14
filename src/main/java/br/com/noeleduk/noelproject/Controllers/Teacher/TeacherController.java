package br.com.noeleduk.noelproject.Controllers.Teacher;

import br.com.noeleduk.noelproject.Dto.Response.ResponseDto;
import br.com.noeleduk.noelproject.Dto.User.CreateUserDto;
import br.com.noeleduk.noelproject.Dto.User.GetUserDto;
import br.com.noeleduk.noelproject.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
  private final TeacherService teacherService;

  @Autowired
  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping("/getAll")
  public ResponseEntity<ResponseDto> getAllUsers() {
    try {
      List<GetUserDto> userEntities = teacherService.getAllTeachers();
      return ResponseEntity.ok().body(
              new ResponseDto("Teachers found", true, userEntities)
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
      GetUserDto user = teacherService.getTeacherByEmail(email);
      return ResponseEntity.ok().body(
              new ResponseDto("User found", true, user)
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
              new ResponseDto("Teacher created with success!", true, teacherService.createTeacher(createUserDto))
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
