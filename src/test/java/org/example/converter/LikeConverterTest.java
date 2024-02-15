package org.example.converter;

import org.example.dto.LikeDTO;
import org.example.entity.Like;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikeConverterTest {
    Date date;

    @Test
    public void testToModel() {
        LikeDTO likeDTO = new LikeDTO("JohnDoe", date, 123L);


        LikeConverter likeConverter = new LikeConverter();
        Like like = likeConverter.toModel(likeDTO);

        assertEquals("JohnDoe", like.getUser_nickname());
        assertEquals(date, like.getCreated());
        assertEquals(123, like.getPost_id());
    }
}
