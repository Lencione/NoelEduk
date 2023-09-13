package br.com.noeleduk.noelproject.Services;

import br.com.noeleduk.noelproject.Dto.Lessons.CreateLessonDto;
import br.com.noeleduk.noelproject.Entities.LessonEntity;
import br.com.noeleduk.noelproject.Repositories.LessonRepository;
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
    LessonEntity lesson = new LessonEntity();
    lesson.setActive(true);
    lesson.setDate(request.getDate());
    lesson.setExam(false);
    lesson.setSubject(request.getSubject());
    repository.save(lesson);
    return true;
  }
}
