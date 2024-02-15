package org.example.dto;

import java.util.Date;

public record CommentDTO(String user_nickname, String text, Date created, Long post_id) {
}
