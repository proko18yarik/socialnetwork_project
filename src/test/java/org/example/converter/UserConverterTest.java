package org.example.converter;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.entity.UserGender;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConverterTest {
    Timestamp created;
    Timestamp updated;

    @Test
    public void testToModel() {
        UserDTO userDTO = new UserDTO("John", created, updated, "London", UserGender.Male);


        UserConverter userConverter = new UserConverter();
        User user = userConverter.toModel(userDTO);

        assertEquals("John", user.getNickname());
        assertEquals(created, user.getCreated());
        assertEquals(updated, user.getUpdated());
        assertEquals("London", user.getLocation());
        assertEquals(UserGender.Male, user.getGender());

    }
}
