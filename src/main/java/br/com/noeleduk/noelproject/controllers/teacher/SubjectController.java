package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/teachers/subjects")
public class SubjectController {
  private final SubjectService subjectService;

  @Autowired
  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

}
