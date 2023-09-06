package br.com.noeleduk.noelproject.Services;

import br.com.noeleduk.noelproject.Dto.User.CreateUserDto;
import br.com.noeleduk.noelproject.Dto.User.GetUserDto;
import br.com.noeleduk.noelproject.Entities.UserEntity;
import br.com.noeleduk.noelproject.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
  }

  public List<GetUserDto> getAllUsers() {
    List<UserEntity> users = userRepository.findAll();

    if (users.isEmpty()) {
      throw new RuntimeException("Users not found");
    }

    return users.stream().map(user -> modelMapper.map(user, GetUserDto.class))
            .collect(Collectors.toList());
  }

  public GetUserDto getUserByEmail(String email) {
    UserEntity user = userRepository.findByEmail(email);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    return modelMapper.map(user, GetUserDto.class);
  }

  public GetUserDto createUser(CreateUserDto createUserDTO) {

    if (userRepository.existsByEmail(createUserDTO.getEmail())) {
      throw new RuntimeException("O E-mail ja esta em uso!");
    }

    if (userRepository.existsByDocument(createUserDTO.getDocument())) {
      throw new RuntimeException("O RA ja esta em uso");
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
    userEntity.setRole("student");
    userEntity.setDocument(createUserDTO.getDocument());
    userEntity.setEdukoins(0);
    userEntity.setAvatar("");
    userEntity.setPoints(0);
    userEntity = userRepository.save(userEntity);
    return modelMapper.map(userEntity, GetUserDto.class);

  }

  public UserEntity updateUser(UUID id, UserEntity userEntity) {
    if (userRepository.existsById(id)) {
      userEntity.setId(id);
      return userRepository.save(userEntity);
    }
    return null; // Or throw an exception indicating that the user doesn't exist
  }

  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
  }


}
