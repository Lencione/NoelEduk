package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.repositories.ClassRepository;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
  private final ClassRepository repository;
  private final UserRepository userRepository;

  @Autowired
  public ClassService(ClassRepository repository, UserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  public List<ClassEntity> getAllClasses() {
    return repository.findAll();
  }

  public ClassEntity createClass(ClassEntity classEntity) {
    return repository.save(classEntity);
  }

  public boolean addStudentToClass(AddStudentToClassDto dto) {
    ClassEntity classEntity = repository.findClassById(dto.getClassId());
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
    repository.save(classEntity);
    return true;
  }
}
