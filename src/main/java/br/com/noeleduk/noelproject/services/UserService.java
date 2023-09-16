package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.lessons.GetUserLessonsDto;
import br.com.noeleduk.noelproject.dto.user.CreateUserDto;
import br.com.noeleduk.noelproject.dto.user.GetUserDto;
import br.com.noeleduk.noelproject.dto.user.LoggedUserDto;
import br.com.noeleduk.noelproject.dto.user.LoginRequestDto;
import br.com.noeleduk.noelproject.entities.Lesson;
import br.com.noeleduk.noelproject.entities.User;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  public UserService(UserRepository repository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
  }

  public List<GetUserDto> getAllUsers() {
    List<User> users = repository.findAllStudents();

    if (users.isEmpty()) {
      throw new RuntimeException("Users not found");
    }

    return users.stream().map(user -> modelMapper.map(user, GetUserDto.class))
            .collect(Collectors.toList());
  }

  public GetUserDto getUserByEmail(String email) {
    User user = repository.findByEmail(email);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public List<GetUserLessonsDto> getStudentLessons(String document){
    User user = repository.findStudentByDocument(document);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    List<Lesson> lessons = repository.getLessonsByUser(user);
    if (lessons.isEmpty()) {
      throw new RuntimeException("Lessons not found");
    }

    return  lessons.stream().map(lesson -> modelMapper.map(lesson, GetUserLessonsDto.class))
            .collect(Collectors.toList());
  }

  public LoggedUserDto create(CreateUserDto createUserDTO) {

    validateUser(createUserDTO);

    User user = createUser(createUserDTO);
    return modelMapper.map(user, LoggedUserDto.class);
  }

  @NotNull
  private User createUser(CreateUserDto createUserDTO) {
    User user = new User();
    user.setEmail(createUserDTO.getEmail());
    user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
    user.setName(createUserDTO.getName());
    user.setCpf(createUserDTO.getCpf());
    user.setRg(createUserDTO.getRg());
    user.setPhone(createUserDTO.getPhone());
    user.setRole("student");
    user.setDocument(createUserDTO.getDocument());
    user.setEdukoins(0);
    user.setAvatar("");
    user.setPoints(0);
    user.setToken(UUID.randomUUID().toString());
    user.setTokenExpiration(LocalDateTime.now().plusDays(7));
    user = repository.save(user);
    return user;
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
    User userEntity = repository.findByEmail(user.getEmail());
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
    User user = repository.findByToken(token);
    if(user != null){
      return !user.getTokenExpiration().isBefore(LocalDateTime.now());
    }
    return false;
  }


}
