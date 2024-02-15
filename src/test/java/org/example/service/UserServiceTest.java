package org.example.service;


import org.example.entity.User;
import org.example.entity.UserGender;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRepositoryImpl userRepositoryImpl;
    @Mock
    private BeanUtilService beanUtilService;
    @InjectMocks
    private UserService userService;

    @Test
    public void testSave_ValidUser_SuccessfullySaved() {
        User user = new User();
        user.setNickname("Rocky");
        user.setLocation("London");
        user.setGender(UserGender.Male);
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);

        verify(userRepository).save(user);
    }

    @Test
    public void testGetAll() {

        List<User> expectedUsers = new ArrayList<>();


        List<User> actualUsers = userService.getAll();


        assertEquals(expectedUsers, actualUsers);

    }

    @Test
    public void testDeleteById() {
        User user = new User();
        user.setNickname("Marta");
        user.setLocation("London");
        user.setGender(UserGender.Male);
        userService.save(user);


        Long userId = user.getId();


        userService.deleteById(userId);

        Optional<User> deletedUser = userRepository.findById(userId);
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testDeleteAll() {

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        userRepository.saveAll(List.of(user1, user2, user3));


        userService.deleteAll();


        List<User> allUsers = userService.getAll();
        assertEquals(0, allUsers.size());

    }

    @Test
    public void testFindUserById() {

        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);


        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));


        User foundUser = userService.findById(userId);


        Assert.assertEquals(mockUser, foundUser);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User();
        User updatedUser = new User();
        existingUser.setId(userId);
        existingUser.setNickname("John");
        updatedUser.setId(userId);
        updatedUser.setNickname("Jane");

        Mockito.when(userRepositoryImpl.findAllNicknames()).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenReturn(updatedUser);


        User result = userService.update(userId, updatedUser);


        Assert.assertEquals(updatedUser, result);
    }


}
