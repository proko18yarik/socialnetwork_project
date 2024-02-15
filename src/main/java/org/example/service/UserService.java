package org.example.service;

import lombok.*;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final BeanUtilService beanUtilService;


    public User save(User user) {

        if (!userRepositoryImpl.findAllNicknames().contains(user.getNickname()) && user.getNickname() != null && user.getLocation() != null && user.getGender() != null) {

            return userRepository.save(user);

        } else throw new RuntimeException("Duplicate!");
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User update(Long id, User user) {
        if (!userRepositoryImpl.findAllNicknames().contains(user.getNickname())) {
            User existing = findById(id);
            beanUtilService.copyProperties(user, existing);
            return userRepository.save(existing);
        } else throw new RuntimeException("Duplicate!");
    }
}
