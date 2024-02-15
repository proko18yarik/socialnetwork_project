package org.example.converter;

import lombok.AllArgsConstructor;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
    public User toModel(UserDTO userDTO) {
        return User.builder().
                nickname(userDTO.nickname()).
                created(userDTO.created()).
                updated(userDTO.updated()).
                location(userDTO.location()).
                gender(userDTO.gender()).
                build();
    }
}
