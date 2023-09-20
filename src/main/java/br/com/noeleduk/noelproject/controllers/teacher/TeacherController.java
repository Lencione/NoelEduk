package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.classes.CreateClassDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.services.TeacherService;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
  private final TeacherService service;

  @Autowired
  public TeacherController(TeacherService service) {
    this.service = service;
  }

  //region Classes
  @PostMapping("/{document}/classes")
  public ResponseEntity<ResponseDto> createClass(@PathVariable String document, @RequestBody CreateClassDto request) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Created class successfully", true, service.createClass(document, request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @GetMapping("/{document}/classes")
  public ResponseEntity<ResponseDto> getClasses(@PathVariable String document) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Classes found successfully", true, service.getClassesByTeacherDocument(document))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("/{document}/classes/{id}/addStudent")
  public ResponseEntity<ResponseDto> addStudentToClass(@PathVariable String document, @PathVariable UUID id, @RequestBody AddStudentToClassDto request) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("User added to class successfully", true, service.addStudentToClass(document, id, request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @GetMapping("/{document}/classes/{id}/getStudents")
  public ResponseEntity<ResponseDto> getStudentsByClassId(@PathVariable("id") UUID id, @PathVariable String document) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Students found successfully", true, service.getStudentsByClassId(document, id))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
  //endregion

  //region Subjects
  @GetMapping("/{document}/subjects")
  public ResponseEntity<ResponseDto> getSubjects(@PathVariable String document) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Subjects found successfully", true, service.getSubjectsByTeacherDocument(document))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }


  @PostMapping("/{document}/subjects")
  public ResponseEntity<ResponseDto> createSubject(@PathVariable String document, @RequestBody CreateSubjectDto request) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Subject created successfully", true, service.createSubject(document, request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("/{document}/subjects/{id}/addClass")
  public ResponseEntity<ResponseDto> addClass(@PathVariable String document, @PathVariable UUID id, @RequestBody AddClassToSubjectDto request) {
    try {
      return ResponseEntity.ok(// Ad class to subject
              new ResponseDto("Class added to subject successfully", true, service.addClassToSubject(document, id, request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @GetMapping("{document}/subjects/{id}/getStudents")
  public ResponseEntity<ResponseDto> getStudentsBySubjectId(@PathVariable("id") UUID id, @PathVariable String document) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Students found successfully", true, service.getStudentsBySubjectId(document, id))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
  //endregion

  //region Lessons
  @GetMapping("/{document}/lessons")
  public ResponseEntity<ResponseDto> getLessonsByTeacherDocument(@PathVariable String document) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Lessons found successfully", true, service.getLessonsByTeacherDocument(document))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("/{document}/lessons/{id}/createToken")
  public ResponseEntity<ResponseDto> createLessonToken(@PathVariable String document, @PathVariable UUID id) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Token created successfully", true, service.createLessonToken(document, id))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
  //endregion

  //region Teachers
  @GetMapping("/")
  public ResponseEntity<ResponseDto> getAllTeachers() {
    try {
      List<GetUserDto> userEntities = service.getAllTeachers();
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
      GetUserDto user = service.getTeacherByEmail(email);
      return ResponseEntity.ok().body(
              new ResponseDto("User found", true, user)
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  //endregion


}
