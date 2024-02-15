package org.example.dto;

import java.util.Date;

public record PostDTO(Date created, Date updated, String description, Long user_id) {
}
