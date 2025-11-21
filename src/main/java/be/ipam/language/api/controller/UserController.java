package be.ipam.language.api.controller;

import be.ipam.language.api.dto.UserEntityDto;
import be.ipam.language.api.mapper.UserEntityMapper;
import be.ipam.language.bll.exceptions.UserException;
import be.ipam.language.bll.services.Iservices.IUserService;
import be.ipam.language.dao.entities.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Scope("singleton")
public class UserController {

    private final IUserService userService;
    private final UserEntityMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserEntityDto>> getAllUsers() {
        List<UserEntityDto> users = userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDto> getUserById(@PathVariable Long id) throws UserException {
        UserEntity user = userService.findUserById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserEntityDto> createUser(@RequestBody UserEntityDto userDto) {
        UserEntity savedUser = userService.saveUser(userMapper.toEntity(userDto));
        return ResponseEntity.ok(userMapper.toDto(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntityDto> updateUser(@PathVariable Long id, @RequestBody UserEntityDto userDto) throws UserException {
        UserEntity existingUser = userService.findUserById(id);
        userMapper.partialUpdate(userDto, existingUser);
        UserEntity updatedUser = userService.saveUser(existingUser);
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
