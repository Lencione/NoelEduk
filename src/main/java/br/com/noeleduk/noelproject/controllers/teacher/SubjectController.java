package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers/subjects")
public class SubjectController {
  private final SubjectService subjectService;

  @Autowired
  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createSubject(@RequestBody CreateSubjectDto request) {
    try {
      return ResponseEntity.ok(
              new ResponseDto("Subject created successfully", true, subjectService.create(request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }

  @PostMapping("/addClass")
  public ResponseEntity<ResponseDto> addClass(@RequestBody AddClassToSubjectDto request) {
    try {
      return ResponseEntity.ok(// Ad class to subject
              new ResponseDto("Class added to subject successfully", true, subjectService.addClass(request))
      );
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(
              new ResponseDto(e.getMessage(), false, null)
      );
    }
  }
}
