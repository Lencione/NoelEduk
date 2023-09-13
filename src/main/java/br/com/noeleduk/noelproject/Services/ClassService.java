package br.com.noeleduk.noelproject.Services;

import br.com.noeleduk.noelproject.Dto.Classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.Entities.ClassEntity;
import br.com.noeleduk.noelproject.Entities.UserEntity;
import br.com.noeleduk.noelproject.Repositories.ClassRepository;
import br.com.noeleduk.noelproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassService {
  private final ClassRepository classRepository;
  private final UserRepository userRepository;

  @Autowired
  public ClassService(ClassRepository classRepository, UserRepository userRepository) {
    this.classRepository = classRepository;
    this.userRepository = userRepository;
  }

  public List<ClassEntity> getAllClasses() {
    return classRepository.findAll();
  }

  public ClassEntity createClass(ClassEntity classEntity) {
    return classRepository.save(classEntity);
  }

  public boolean addStudentToClass(AddStudentToClassDto dto) {
    ClassEntity classEntity = classRepository.findClassEntityById(dto.getClassId());
    UserEntity user = userRepository.findStudentByDocument(dto.getStudentRa());

    if (classEntity == null) {
      throw new RuntimeException("Class not found");
    }

    if (user == null) {
      throw new RuntimeException("Student not found");
    }

    if (classEntity.getStudents().contains(user)) {
      throw new RuntimeException("Student already in class");
    }

    if (!user.getRole().equals("student")) {
      throw new RuntimeException("User is not a student");
    }

    classEntity.getStudents().add(user);
    classRepository.save(classEntity);
    return true;
  }
}
