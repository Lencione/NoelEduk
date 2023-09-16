package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.LoggedUserDto;
import br.com.noeleduk.noelproject.entities.User;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  @Autowired
  public TeacherService(UserRepository repository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
  }

  public List<GetUserDto> getAllTeachers() {
    List<User> users = repository.findAllTeachers();

    if (users.isEmpty()) {
      throw new RuntimeException("Teachers not found");
    }

    return users.stream().map(user -> modelMapper.map(user, GetUserDto.class))
            .collect(Collectors.toList());
  }

  public GetUserDto getTeacherByEmail(String email) {
    User user = repository.findTeacherByEmail(email);
    if (user == null) {
      throw new RuntimeException("Teacher not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }
  public GetUserDto getTeacherByDocument(String document) {
    User user = repository.findTeacherByDocument(document);
    if (user == null) {
      throw new RuntimeException("Teacher not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public LoggedUserDto createTeacher(CreateUserDto createUserDTO) {

    if (repository.existsByEmail(createUserDTO.getEmail())) {
      throw new RuntimeException("O E-mail ja esta em uso!");
    }

    if (repository.existsByDocument(createUserDTO.getDocument())) {
      throw new RuntimeException("O Registro ja esta em uso");
    }

    if (repository.existsByCpf(createUserDTO.getCpf())) {
      throw new RuntimeException("O CPF ja esta em uso");
    }

    if (repository.existsByRg(createUserDTO.getRg())) {
      throw new RuntimeException("O RG ja esta em uso");
    }

    User user = new User();
    user.setEmail(createUserDTO.getEmail());
    user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
    user.setName(createUserDTO.getName());
    user.setCpf(createUserDTO.getCpf());
    user.setRg(createUserDTO.getRg());
    user.setPhone(createUserDTO.getPhone());
    user.setRole("teacher");
    user.setDocument(createUserDTO.getDocument());
    user.setEdukoins(0);
    user.setAvatar("");
    user.setPoints(0);
    user = repository.save(user);
    user.setToken(UUID.randomUUID().toString());
    user.setTokenExpiration(LocalDateTime.now().plusDays(7));
    return modelMapper.map(user, LoggedUserDto.class);
  }

  public boolean validateToken(String token) {
    User teacher = repository.findTeacherByToken(token);
    if(teacher != null){
      return !teacher.getTokenExpiration().isBefore(LocalDateTime.now());
    }
    return false;
  }



}
