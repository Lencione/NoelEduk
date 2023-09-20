package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.classes.CreateClassDto;
import br.com.noeleduk.noelproject.dto.classes.GetClassDto;
import br.com.noeleduk.noelproject.dto.lessons.GetLessonDto;
import br.com.noeleduk.noelproject.dto.lessons.GetUserLessonsDto;
import br.com.noeleduk.noelproject.dto.subjects.AddClassToSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.dto.subjects.GetSubjectDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.LoggedUserDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.entities.LessonEntity;
import br.com.noeleduk.noelproject.entities.SubjectEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.helpers.Utils;
import br.com.noeleduk.noelproject.repositories.ClassRepository;
import br.com.noeleduk.noelproject.repositories.LessonRepository;
import br.com.noeleduk.noelproject.repositories.SubjectRepository;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;
  private final SubjectRepository subjectRepository;

  private final ClassRepository classRepository;
  private final LessonRepository lessonRepository;


  @Autowired
  public TeacherService(
          UserRepository repository,
          PasswordEncoder passwordEncoder,
          ModelMapper modelMapper,
          SubjectRepository subjectRepository,
          ClassRepository classRepository,
          LessonRepository lessonRepository) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
    this.subjectRepository = subjectRepository;
    this.classRepository = classRepository;
    this.lessonRepository = lessonRepository;
  }

  public List<GetUserDto> getAllTeachers() {
    List<UserEntity> users = repository.findAllTeachers();
    System.out.println((long) users.size());
    if (users.isEmpty()) {
      throw new RuntimeException("Teachers not found");
    }

    return users.stream().map(user -> modelMapper.map(user, GetUserDto.class))
            .collect(Collectors.toList());
  }

  public List<GetSubjectDto> getSubjectsByTeacherDocument(String document) {
    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher email");
    }
    return subjectRepository.findSubjectsByTeacher(teacher).stream().map(subject -> modelMapper.map(subject, GetSubjectDto.class)).collect(Collectors.toList());
  }

  public GetUserDto getTeacherByEmail(String email) {
    UserEntity user = repository.findTeacherByEmail(email);
    if (user == null) {
      throw new RuntimeException("Teacher not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public GetUserDto getTeacherByDocument(String document) {
    UserEntity user = repository.findTeacherByDocument(document);
    if (user == null) {
      throw new RuntimeException("Teacher not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public List<GetLessonDto> getLessonsByTeacherDocument(String document) {
    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher email");
    }
    return teacher.getSubjects().stream()
            .flatMap(subject -> subject.getLessons().stream())
            .map(lesson -> modelMapper.map(lesson, GetLessonDto.class))
            .collect(Collectors.toList());
  }

  public List<GetUserDto> getStudentsBySubjectId(String document, UUID id) {

    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }

    SubjectEntity subject = subjectRepository.findSubjectById(id);
    if (subject == null) {
      throw new RuntimeException("Subject not found");
    }

    if(subject.getTeacher() != teacher) {
      throw new RuntimeException("Subject does not belong to teacher");
    }

    List<UserEntity> students = subject.getClasses().stream().flatMap(classEntity -> classEntity.getStudents().stream()).collect(Collectors.toList());
    if (students.isEmpty()) {
      throw new RuntimeException("No students found");
    }
    return students.stream().map(student -> modelMapper.map(student, GetUserDto.class)).collect(Collectors.toList());
  }

  public boolean validateToken(String token) {
    UserEntity teacher = repository.findTeacherByToken(token);
    if (teacher != null) {
      return !teacher.getTokenExpiration().isBefore(LocalDateTime.now());
    }
    return false;
  }


  public GetSubjectDto createSubject(String document, CreateSubjectDto request) {
    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }
    SubjectEntity subject = new SubjectEntity();
    subject.setName(request.getName());
    subject.setTeacher(teacher);
    subject.setGoogleCode(request.getGoogle_code());
    subject.setWeek_day(request.getWeek_day());
    subject.setStart_date(Utils.getDateFromLocalDate(request.getStart_date()));
    subject.setEnd_date(Utils.getDateFromLocalDate(request.getEnd_date()));
    subject = subjectRepository.save(subject);

    return modelMapper.map(subject, GetSubjectDto.class);

  }


  public String addClassToSubject(String document, UUID id, AddClassToSubjectDto request) {
    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }
    SubjectEntity subject = subjectRepository.findSubjectById(id);
    if (subject == null) {
      throw new RuntimeException("Invalid subject id");
    }

    if (subject.getTeacher().getId() != teacher.getId()) {
      throw new RuntimeException("This subject does not belong to this teacher");
    }

    ClassEntity classEntity = classRepository.findClassById(request.getClass_id());
    if (classEntity == null) {
      throw new RuntimeException("Invalid class id");
    }

    if (classEntity.getSubjects().contains(subject)) {
      throw new RuntimeException("Class already in subject");
    }

    subject.getClasses().add(classEntity);
    subjectRepository.save(subject);
    return "Class added to subject with success";
  }

  public String createClass(String document, CreateClassDto request) {
    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }

    ClassEntity classEntity = new ClassEntity();
    classEntity.setName(request.getName());
    classEntity.setSemester(request.getSemester());
    classRepository.save(classEntity);

    return "Class created with success";
  }

  public Object getClassesByTeacherDocument(String document) {
    UserEntity teacher = repository.findTeacherByDocument(document);
    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }
    return classRepository.findAll().stream().map(classEntity -> modelMapper.map(classEntity, GetClassDto.class)).collect(Collectors.toList());
  }

  public String addStudentToClass(String document, UUID id, AddStudentToClassDto request) {
    UserEntity teacher = repository.findTeacherByDocument(document);

    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }

    ClassEntity classEntity = classRepository.findClassById(id);
    if (classEntity == null) {
      throw new RuntimeException("Invalid class id");
    }

    UserEntity student = repository.findStudentByDocument(request.getDocument());
    if (student == null) {
      throw new RuntimeException("Invalid student document");
    }

    if (classEntity.getStudents().contains(student)) {
      throw new RuntimeException("Student already in class");
    }

    classEntity.getStudents().add(student);
    classRepository.save(classEntity);
    return "Student added to class with success";
  }

  public Object getStudentsByClassId(String document, UUID id) {
    UserEntity teacher = repository.findTeacherByDocument(document);

    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }

    ClassEntity classEntity = classRepository.findClassById(id);
    if (classEntity == null) {
      throw new RuntimeException("Invalid class id");
    }

    return classEntity.getStudents().stream().map(student -> modelMapper.map(student, GetUserDto.class)).collect(Collectors.toList());
  }

  public String createLessonToken(String document, UUID id) {
    UserEntity teacher = repository.findTeacherByDocument(document);

    if (teacher == null) {
      throw new RuntimeException("Invalid teacher document");
    }

    LessonEntity lesson = lessonRepository.findLessonById(id);
    if (lesson == null) {
      throw new RuntimeException("Invalid lesson id");
    }

    if(lesson.getSubject().getTeacher().getId() != teacher.getId()){
      throw new RuntimeException("This lesson does not belong to this teacher");
    }

    //create exclusive token and set in lesson.token and set expiration date after 10seconds
    String token = UUID.randomUUID().toString();
    lesson.setToken(token);
    lesson.setToken_expiration(LocalDateTime.now().plusSeconds(10));
    lessonRepository.save(lesson);
    return token;
  }
}
