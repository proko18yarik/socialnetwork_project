package org.example.converter;

import org.example.dto.PostDTO;
import org.example.entity.Post;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostConverterTest {
    Date date;
    Date updated;

    @Test
    public void testToModel() {
        PostDTO postDTO = new PostDTO(date, updated, "Hallo world!", 123L);


        PostConverter postConverter = new PostConverter();
        Post post = postConverter.toModel(postDTO);

        assertEquals(date, post.getCreated());
        assertEquals(updated, post.getUpdated());
        assertEquals("Hallo world!", post.getDescription());
        assertEquals(123, post.getUser_id());

    }
}
