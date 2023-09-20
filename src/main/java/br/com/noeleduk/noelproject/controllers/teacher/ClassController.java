package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers/classes")
public class ClassController {
  private final ClassService classService;

  @Autowired
  public ClassController(ClassService classService) {
    this.classService = classService;
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createClass(@RequestBody ClassEntity request) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Created class successfully", true, classService.createClass(request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("/addUser")
  public ResponseEntity<ResponseDto> addStudentToClass(@RequestBody AddStudentToClassDto request) {
    try{
      return ResponseEntity.ok(
              new ResponseDto("User added to class successfully", true, classService.addStudentToClass(request))
      );
    }catch (Exception e){
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
