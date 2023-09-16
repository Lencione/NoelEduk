package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.lessons.CreateLessonDto;
import br.com.noeleduk.noelproject.entities.Lesson;
import br.com.noeleduk.noelproject.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
  private final LessonRepository repository;

  @Autowired
  public LessonService(LessonRepository repository) {
    this.repository = repository;
  }

  public boolean create(CreateLessonDto request){
    Lesson lesson = new Lesson();
    lesson.setActive(true);
    lesson.setDate(request.getDate());
    lesson.setExam(false);
    lesson.setSubject(request.getSubject());
    repository.save(lesson);
    return true;
  }
}
