package br.com.noeleduk.noelproject.Services;

import br.com.noeleduk.noelproject.Dto.Lessons.CreateLessonDto;
import br.com.noeleduk.noelproject.Dto.Subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.Dto.Subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.Entities.ClassEntity;
import br.com.noeleduk.noelproject.Entities.SubjectEntity;
import br.com.noeleduk.noelproject.Entities.UserEntity;
import br.com.noeleduk.noelproject.Repositories.ClassRepository;
import br.com.noeleduk.noelproject.Repositories.SubjectRepository;
import br.com.noeleduk.noelproject.Repositories.UserRepository;
import jdk.swing.interop.SwingInterOpUtils;
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
    UserEntity teacher = getValidatedTeacher(request.getDocument());

    SubjectEntity subject = createSubject(request, teacher);
    generateLessons(subject);

    return true;
  }

  @Transactional
  public boolean addClass(AddClassToSubjectDto request){
    SubjectEntity subject = repository.findSubjectById(request.getSubject_id());
    if(subject == null){
      throw new RuntimeException("Invalid subject id");
    }

    ClassEntity classEntity = classRepository.findClassById(request.getClass_id());
    if(classEntity == null){
      throw new RuntimeException("Invalid class id");
    }

    if(subject.getClasses().contains(classEntity)){
      throw new RuntimeException("Class already in subject");
    }

    classEntity.getSubjects().add(subject);
    repository.save(subject);
    return true;
  }

  //PRIVATE FUNCTIONS
  private UserEntity getValidatedTeacher(String document)  {
    UserEntity teacher = userRepository.findTeacherByDocument(document);
    if (teacher == null || !teacher.getRole().equals("teacher")) {
      throw new RuntimeException("Invalid teacher document");
    }
    return teacher;
  }

  private SubjectEntity createSubject(CreateSubjectDto request, UserEntity teacher) {
    SubjectEntity subject = new SubjectEntity();
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

  private void generateLessons(SubjectEntity subject) {
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
