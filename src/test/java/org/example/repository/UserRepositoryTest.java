package org.example.repository;

import org.example.entity.User;
import org.example.entity.UserGender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setNickname("John");
        user.setLocation("London");
        user.setGender(UserGender.Male);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals(user, savedUser);
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setNickname("John");
        user.setLocation("London");
        user.setGender(UserGender.Male);

        User savedUser = userRepository.save(user);

        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);

        assertNotNull(foundUser);
        assertEquals(savedUser, foundUser);
        assertEquals(savedUser, foundUser);
    }

    @Test
    public void testFindAllUsers() {
        User user = new User();
        user.setNickname("Mark");
        user.setLocation("London");
        user.setGender(UserGender.Male);
        userRepository.save(user);

        User user2 = new User();
        user2.setNickname("Lola");
        user2.setLocation("Krakow");
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertEquals(userRepository.findAllNicknames().size(), users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }

    @Test
    public void testDeleteAllUsers() {
        User user = new User();
        user.setNickname("John");
        user.setLocation("London");
        user.setGender(UserGender.Male);
        userRepository.save(user);

        User user2 = new User();
        user2.setNickname("Marta");
        user2.setLocation("London");
        user2.setGender(UserGender.Male);
        userRepository.save(user2);

        userRepository.deleteAll();

        List<User> users = userRepository.findAll();

        assertEquals(0, users.size());
    }

    @Test
    public void testDeleteUserById() {
        User user = new User();
        user.setNickname("Tom");
        user.setLocation("London");
        user.setGender(UserGender.Male);
        User savedUser = userRepository.save(user);

        Long userId = savedUser.getId();
        userRepository.deleteById(userId);

        assertFalse(userRepository.existsById(userId));
    }

}


