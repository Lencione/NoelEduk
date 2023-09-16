package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.lessons.CreateLessonDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.entities.Class;
import br.com.noeleduk.noelproject.entities.Subject;
import br.com.noeleduk.noelproject.entities.User;
import br.com.noeleduk.noelproject.repositories.ClassRepository;
import br.com.noeleduk.noelproject.repositories.SubjectRepository;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SubjectService {
  private final SubjectRepository repository;
  private final UserRepository userRepository;
  private final LessonService lessonService;

  private final ClassRepository classRepository;

  @Autowired
  public SubjectService(
          SubjectRepository repository,
          UserRepository userRepository,
          LessonService lessonService,
          ClassRepository classRepository
  ) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.lessonService = lessonService;
    this.classRepository = classRepository;
  }

  public boolean create(CreateSubjectDto request) {
    User teacher = getValidatedTeacher(request.getDocument());

    Subject subject = createSubject(request, teacher);
    generateLessons(subject);

    return true;
  }

  @Transactional
  public boolean addClass(AddClassToSubjectDto request){
    Subject subject = repository.findSubjectById(request.getSubject_id());
    if(subject == null){
      throw new RuntimeException("Invalid subject id");
    }

    Class aClass = classRepository.findClassById(request.getClass_id());
    if(aClass == null){
      throw new RuntimeException("Invalid class id");
    }

    if(subject.getClasses().contains(aClass)){
      throw new RuntimeException("Class already in subject");
    }

    aClass.getSubjects().add(subject);
    repository.save(subject);
    return true;
  }

  //PRIVATE FUNCTIONS
  private User getValidatedTeacher(String document)  {
    User teacher = userRepository.findTeacherByDocument(document);
    if (teacher == null || !teacher.getRole().equals("teacher")) {
      throw new RuntimeException("Invalid teacher document");
    }
    return teacher;
  }

  private Subject createSubject(CreateSubjectDto request, User teacher) {
    Subject subject = new Subject();
    subject.setName(request.getName());
    subject.setTeacher(teacher);
    subject.setGoogleCode(request.getGoogle_code());
    subject.setWeek_day(request.getWeek_day());
    subject.setStart_date(getDateFromLocalDate(request.getStart_date()));
    subject.setEnd_date(getDateFromLocalDate(request.getEnd_date()));
    return repository.save(subject);
  }

  private Date getDateFromLocalDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  private void generateLessons(Subject subject) {
    LocalDate initialDate = subject.getStart_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().toLocalDate();
    int currentWeekDay = initialDate.getDayOfWeek().getValue();
    int daysUntilNextWeekDay = (subject.getWeek_day() - currentWeekDay + 7) % 7;
    LocalDate nextWeekDay = initialDate.plusDays(daysUntilNextWeekDay);
    LocalDate endDate = subject.getEnd_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().toLocalDate();

    while (nextWeekDay.isBefore(endDate) || nextWeekDay.isEqual(endDate)) {
      CreateLessonDto lesson = new CreateLessonDto(
              Date.from(nextWeekDay.atStartOfDay(ZoneId.systemDefault()).toInstant()),
              subject
      );
      lessonService.create(lesson);
      nextWeekDay = nextWeekDay.plusDays(7);
    }
  }


}
