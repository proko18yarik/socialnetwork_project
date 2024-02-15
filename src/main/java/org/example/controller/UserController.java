package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.converter.UserConverter;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public User save(@RequestBody UserDTO userDTO) {
        User user = userConverter.toModel(userDTO);
        return userService.save(user);
    }

    @GetMapping
    @ResponseStatus()
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        userService.deleteAll();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userConverter.toModel(userDTO);
        return userService.update(id, user);
    }
}
