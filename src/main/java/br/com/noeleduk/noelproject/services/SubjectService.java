package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.lessons.CreateLessonDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.dto.user.GetUserPresenceDto;
import br.com.noeleduk.noelproject.entities.SubjectEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubjectService {
  private final SubjectRepository repository;
  private final LessonService lessonService;

  @Autowired
  public SubjectService(
          SubjectRepository repository,
          LessonService lessonService
  ) {
    this.repository = repository;
    this.lessonService = lessonService;
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

  public List<GetUserPresenceDto> getSubjectsByStudent(UserEntity student) {
    List<Object[]> results = repository.findAllSubjectsByUserId(student.getId());
    if (results.isEmpty()) {
      throw new RuntimeException("No subjects found");
    }

    List<GetUserPresenceDto> userPresenceDtoList = new ArrayList<>();

    for (Object[] result : results) {
      GetUserPresenceDto userPresenceDto = new GetUserPresenceDto();
      userPresenceDto.setSubjectName((String) result[0]);
      userPresenceDto.setTotal((Long) result[1]);
      userPresenceDto.setPresences((Long) result[2]);
      userPresenceDto.setFouls((Long) result[3]);
      userPresenceDtoList.add(userPresenceDto);
    }

    return userPresenceDtoList;
  }
}
