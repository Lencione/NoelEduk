package br.com.noeleduk.noelproject.Controllers;

import br.com.noeleduk.noelproject.Dto.ResponseDto;
import br.com.noeleduk.noelproject.Dto.User.CreateUserDto;
import br.com.noeleduk.noelproject.Dto.User.UserResponseDto;
import br.com.noeleduk.noelproject.Entities.UserEntity;
import br.com.noeleduk.noelproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAllUsers() {
        List<UserResponseDto> userEntities = userService.getAllUsers();
        return ResponseEntity.ok().body(new ResponseDto("success","Usuarios listados com sucesso!",userEntities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable UUID id) {
        Optional<UserEntity> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDto createUserDto) {
        UserEntity createdUserEntity = userService.createUser(createUserDto);
        return new ResponseEntity<>(createdUserEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable UUID id, @RequestBody UserEntity userEntity) {
        UserEntity updatedUserEntity = userService.updateUser(id, userEntity);
        return ResponseEntity.ok(updatedUserEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
