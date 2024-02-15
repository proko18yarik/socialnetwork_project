package org.example.converter;

import lombok.AllArgsConstructor;
import org.example.dto.LikeDTO;
import org.example.dto.PostDTO;
import org.example.entity.Like;
import org.example.entity.Post;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LikeConverter {
    public Like toModel(LikeDTO likeDTO) {
        return Like.builder().
                user_nickname(likeDTO.user_nickname()).
                created(likeDTO.created()).
                post_id(likeDTO.post_id()).
                build();
    }
}
