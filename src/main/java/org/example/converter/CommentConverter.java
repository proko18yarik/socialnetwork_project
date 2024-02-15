package org.example.converter;

import lombok.AllArgsConstructor;
import org.example.dto.CommentDTO;
import org.example.entity.Comment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentConverter {
    public Comment toModel(CommentDTO commentDTO) {
        return Comment.builder().
                user_nickname(commentDTO.user_nickname()).
                text(commentDTO.text()).
                created(commentDTO.created()).
                post_id(commentDTO.post_id()).
                build();
    }
}
