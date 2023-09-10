package br.com.noeleduk.noelproject.Services;

import br.com.noeleduk.noelproject.Dto.User.CreateUserDto;
import br.com.noeleduk.noelproject.Dto.User.GetUserDto;
import br.com.noeleduk.noelproject.Dto.User.LoggedUserDto;
import br.com.noeleduk.noelproject.Dto.User.LoginRequestDto;
import br.com.noeleduk.noelproject.Entities.UserEntity;
import br.com.noeleduk.noelproject.Repositories.UserRepository;
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
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  @Autowired
  public TeacherService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
  }

  public List<GetUserDto> getAllTeachers() {
    List<UserEntity> users = userRepository.findAllTeachers();

    if (users.isEmpty()) {
      throw new RuntimeException("Teachers not found");
    }

    return users.stream().map(user -> modelMapper.map(user, GetUserDto.class))
            .collect(Collectors.toList());
  }

  public GetUserDto getTeacherByEmail(String email) {
    UserEntity user = userRepository.findTeacherByEmail(email);
    if (user == null) {
      throw new RuntimeException("Teacher not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public LoggedUserDto createTeacher(CreateUserDto createUserDTO) {

    if (userRepository.existsByEmail(createUserDTO.getEmail())) {
      throw new RuntimeException("O E-mail ja esta em uso!");
    }

    if (userRepository.existsByDocument(createUserDTO.getDocument())) {
      throw new RuntimeException("O Registro ja esta em uso");
    }

    if (userRepository.existsByCpf(createUserDTO.getCpf())) {
      throw new RuntimeException("O CPF ja esta em uso");
    }

    if (userRepository.existsByRg(createUserDTO.getRg())) {
      throw new RuntimeException("O RG ja esta em uso");
    }

    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(createUserDTO.getEmail());
    userEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
    userEntity.setName(createUserDTO.getName());
    userEntity.setCpf(createUserDTO.getCpf());
    userEntity.setRg(createUserDTO.getRg());
    userEntity.setPhone(createUserDTO.getPhone());
    userEntity.setRole("teacher");
    userEntity.setDocument(createUserDTO.getDocument());
    userEntity.setEdukoins(0);
    userEntity.setAvatar("");
    userEntity.setPoints(0);
    userEntity = userRepository.save(userEntity);
    userEntity.setToken(UUID.randomUUID().toString());
    userEntity.setTokenExpiration(LocalDateTime.now().plusDays(7));
    return modelMapper.map(userEntity, LoggedUserDto.class);
  }

  public boolean validateToken(String token) {
    UserEntity teacher = userRepository.findTeacherByToken(token);
    if(teacher != null){
      return !teacher.getTokenExpiration().isBefore(LocalDateTime.now());
    }
    return false;
  }

}
