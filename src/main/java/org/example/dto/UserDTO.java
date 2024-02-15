package org.example.dto;

import org.example.entity.UserGender;

import java.sql.Timestamp;

public record UserDTO(String nickname, Timestamp created, Timestamp updated, String location, UserGender gender) {
}
