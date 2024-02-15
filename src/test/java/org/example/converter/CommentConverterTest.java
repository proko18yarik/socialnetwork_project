package org.example.converter;

import org.example.dto.CommentDTO;
import org.example.entity.Comment;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentConverterTest {
    Date date;

    @Test
    public void testToModel() {
        CommentDTO commentDTO = new CommentDTO("JohnDoe", "Hello, world!", date, 123L);


        CommentConverter commentConverter = new CommentConverter();
        Comment comment = commentConverter.toModel(commentDTO);

        assertEquals("JohnDoe", comment.getUser_nickname());
        assertEquals("Hello, world!", comment.getText());
        assertEquals(date, comment.getCreated());
        assertEquals(123, comment.getPost_id());
    }
}
