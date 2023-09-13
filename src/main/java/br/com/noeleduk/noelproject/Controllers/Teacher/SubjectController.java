package br.com.noeleduk.noelproject.Controllers.Teacher;

import br.com.noeleduk.noelproject.Dto.Response.ResponseDto;
import br.com.noeleduk.noelproject.Dto.Subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.Services.SubjectService;
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
}
