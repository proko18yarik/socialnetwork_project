package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.converter.CommentConverter;
import org.example.entity.Comment;
import org.example.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @MockBean
    CommentService commentService;
    @MockBean
    CommentConverter commentConverter;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void saveTest() throws Exception {
        Comment comment = new Comment();

        when(commentService.save(comment)).thenReturn(comment);
        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comment)))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception {

        List<Comment> commentList = new ArrayList<>();
        when(commentService.getAll()).thenReturn(commentList);
        mockMvc.perform(get("/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    public void getCommentByIdTest() throws Exception {
        Comment comment = new Comment();
        when(commentService.getById(100L)).thenReturn(comment);
        mockMvc.perform(get("/comments/{id}", 100L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteCommentByIdTest() throws Exception {
        Comment comment = new Comment();
        Long l = 100L;
        comment.setId(l);
        doNothing().when(commentService).deleteById(comment.getId());

        mockMvc.perform(delete("/comments/{id}", comment.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {
        Comment comment = new Comment();
        comment.setId(34L);
        String requestURL = "/comments" + "/" + comment.getId();
        when(commentService.update(comment.getId(), comment)).thenReturn(comment);

        mockMvc.perform(put(requestURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(comment))).andExpect(status().isOk());


    }
}
