package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.classes.AddStudentToClassDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.entities.ClassEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.repositories.ClassRepository;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassService {
  private final ClassRepository repository;
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public ClassService(ClassRepository repository, UserRepository userRepository,  ModelMapper modelMapper) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  public List<ClassEntity> getAllClasses() {
    return repository.findAll();
  }

  public ClassEntity createClass(ClassEntity classEntity) {
    return repository.save(classEntity);
  }

}
