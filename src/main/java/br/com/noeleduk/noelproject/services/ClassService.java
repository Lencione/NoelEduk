package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.entities.Class;
import br.com.noeleduk.noelproject.entities.User;
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

  public List<Class> getAllClasses() {
    return repository.findAll();
  }

  public Class createClass(Class aClass) {
    return repository.save(aClass);
  }

  public boolean addStudentToClass(AddStudentToClassDto dto) {
    Class aClass = repository.findClassById(dto.getClassId());
    User user = userRepository.findStudentByDocument(dto.getStudentRa());

    if (aClass == null) {
      throw new RuntimeException("Class not found");
    }

    if (user == null) {
      throw new RuntimeException("Student not found");
    }

    if (aClass.getStudents().contains(user)) {
      throw new RuntimeException("Student already in class");
    }

    if (!user.getRole().equals("student")) {
      throw new RuntimeException("User is not a student");
    }

    aClass.getStudents().add(user);
    repository.save(aClass);
    return true;
  }
}
