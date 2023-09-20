package br.com.noeleduk.noelproject.controllers.teacher;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/teachers/classes")
public class ClassController {
  private final ClassService classService;

  @Autowired
  public ClassController(ClassService classService) {
    this.classService = classService;
  }







}
