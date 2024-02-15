package org.example.converter;

import org.example.dto.PostDTO;
import org.example.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    public Post toModel(PostDTO postDTO) {
        return Post.builder().
                description(postDTO.description()).user_id(postDTO.user_id()).build();
    }
}
