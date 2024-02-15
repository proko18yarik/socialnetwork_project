package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.converter.LikeConverter;
import org.example.entity.Like;
import org.example.service.LikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@WebMvcTest(LikeController.class)
public class LikeControllerTest {

    @MockBean
    LikeService likeService;
    @MockBean
    LikeConverter likeConverter;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void saveTest() throws Exception {
        Like like = new Like();

        when(likeService.save(like)).thenReturn(like);
        mockMvc.perform(post("/likes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(like)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteLikeByIdTest() throws Exception {
        Like like = new Like();
        Long l = 100L;
        like.setId(l);
        doNothing().when(likeService).deleteById(like.getId());

        mockMvc.perform(delete("/likes/{id}", like.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
