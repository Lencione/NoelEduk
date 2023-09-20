package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.lessons.GetUserLessonsDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.LoggedUserDto;
import br.com.noeleduk.noelproject.dto.user.LoginRequestDto;
import br.com.noeleduk.noelproject.entities.LessonEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  private AuthenticationManager authenticationManager;

  @Autowired
  public UserService(UserRepository repository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
  }

  public List<GetUserDto> getAllUsers() {
    List<UserEntity> users = repository.findAllStudents();

    if (users.isEmpty()) {
      throw new RuntimeException("Users not found");
    }

    return users.stream().map(user -> modelMapper.map(user, GetUserDto.class))
            .collect(Collectors.toList());
  }

  public GetUserDto getUserByEmail(String email) {
    UserEntity user = repository.findByEmail(email);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public List<GetUserLessonsDto> getStudentLessons(String document) {
    UserEntity user = repository.findStudentByDocument(document);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    List<LessonEntity> lessons = repository.getLessonsByUser(user);
    if (lessons.isEmpty()) {
      throw new RuntimeException("Lessons not found");
    }

    return lessons.stream().map(lesson -> modelMapper.map(lesson, GetUserLessonsDto.class))
            .collect(Collectors.toList());
  }

  public LoggedUserDto create(CreateUserDto createUserDTO) {

    validateUser(createUserDTO);

    UserEntity userEntity = createUser(createUserDTO);
    return modelMapper.map(userEntity, LoggedUserDto.class);
  }

  @NotNull
  private UserEntity createUser(CreateUserDto createUserDTO) {

    //if email has al.unieduk.com.br setRole = student


    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(createUserDTO.getEmail());
    userEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
    userEntity.setName(createUserDTO.getName());
    userEntity.setCpf(createUserDTO.getCpf());
    userEntity.setRg(createUserDTO.getRg());
    userEntity.setPhone(createUserDTO.getPhone());

    if (createUserDTO.getEmail().contains("al.unieduk.com.br")) {
      userEntity.setRole("student");
    } else if (createUserDTO.getEmail().contains("prof.unieduk.com.br")) {
      userEntity.setRole("teacher");
    } else {
      throw new RuntimeException("Email invalido (prof.unieduk.com.br ou al.unieduk.com.br)");
    }

    userEntity.setDocument(createUserDTO.getDocument());
    userEntity.setEdukoins(0);
    userEntity.setAvatar("");
    userEntity.setPoints(0);
    userEntity.setToken(UUID.randomUUID().toString());
    userEntity.setTokenExpiration(LocalDateTime.now().plusDays(7));
    userEntity = repository.save(userEntity);
    return userEntity;
  }

  private void validateUser(CreateUserDto createUserDTO) {
    if (repository.existsByEmail(createUserDTO.getEmail())) {
      throw new RuntimeException("O E-mail ja esta em uso!");
    }

    if (repository.existsByDocument(createUserDTO.getDocument())) {
      throw new RuntimeException("O RA ja esta em uso");
    }

    if (repository.existsByCpf(createUserDTO.getCpf())) {
      throw new RuntimeException("O CPF ja esta em uso");
    }

    if (repository.existsByRg(createUserDTO.getRg())) {
      throw new RuntimeException("O RG ja esta em uso");
    }
  }

  public LoggedUserDto login(LoginRequestDto user) {
    UserEntity userEntity = repository.findByEmail(user.getEmail());
    

    if (userEntity == null) {
      throw new RuntimeException("User not found");
    }
    if (passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
      userEntity.setToken(UUID.randomUUID().toString());
      userEntity.setTokenExpiration(LocalDateTime.now().plusDays(7));

      repository.save(userEntity);
      return modelMapper.map(userEntity, LoggedUserDto.class);
    }
    throw new RuntimeException("Invalid password");
  }

  public boolean validateToken(String token) {
    UserEntity user = repository.findByToken(token);
    if (user != null) {
      return !user.getTokenExpiration().isBefore(LocalDateTime.now());
    }
    return false;
  }


}
