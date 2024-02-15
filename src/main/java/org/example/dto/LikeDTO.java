package org.example.dto;

import java.util.Date;

public record LikeDTO(String user_nickname, Date created, Long post_id) {
}
