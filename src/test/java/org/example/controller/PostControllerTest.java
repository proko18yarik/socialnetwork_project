package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.converter.PostConverter;
import org.example.entity.Post;
import org.example.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@WebMvcTest(PostController.class)
public class PostControllerTest {
    @MockBean
    PostService postService;
    @MockBean
    PostConverter postConverter;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveTest() throws Exception {
        Post post = new Post();

        when(postService.save(post)).thenReturn(post);
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(post)))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception {

        List<Post> postList = new ArrayList<>();
        when(postService.getAll()).thenReturn(postList);
        mockMvc.perform(get("/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getUserByIdTest() throws Exception {
        Post post = new Post();
        when(postService.findById(100L)).thenReturn(post);
        mockMvc.perform(get("/posts/{id}", 100L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
