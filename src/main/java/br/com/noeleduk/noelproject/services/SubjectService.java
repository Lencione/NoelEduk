package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.lessons.CreateLessonDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.GetSubjectDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.entities.SubjectEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.repositories.ClassRepository;
import br.com.noeleduk.noelproject.repositories.SubjectRepository;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectService {
  private final SubjectRepository repository;
  private final UserRepository userRepository;
  private final LessonService lessonService;
  private final ClassRepository classRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public SubjectService(
          SubjectRepository repository,
          UserRepository userRepository,
          LessonService lessonService,
          ClassRepository classRepository,
          ModelMapper modelMapper
  ) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.lessonService = lessonService;
    this.classRepository = classRepository;
    this.modelMapper = modelMapper;
  }

  public SubjectEntity create(UserEntity teacher, CreateSubjectDto request) {
    SubjectEntity subject = createSubject(request, teacher);
    generateLessons(subject);
    return subject;
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
