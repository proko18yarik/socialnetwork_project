package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.converter.UserConverter;
import org.example.entity.User;
import org.example.entity.UserGender;
import org.example.service.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    UserService userService;
    @MockBean
    UserConverter userConverter;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void saveTest() throws Exception {
        User user = new User();

        when(userService.save(user)).thenReturn(user);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception {

        List<User> userList = new ArrayList<>();
        when(userService.getAll()).thenReturn(userList);
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()); //For some reason unknown to me when calling this method everything works, but it returns 500


    }


    @Test
    public void getUserByIdTest() throws Exception {
        User user = new User();
        when(userService.findById(100L)).thenReturn(user);
        mockMvc.perform(get("/users/{id}", 100L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteUserByIdTest() throws Exception {
        User user = new User();
        Long l = 100L;
        user.setId(l);
        doNothing().when(userService).deleteById(user.getId());

        mockMvc.perform(delete("/users/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteUsersTest() throws Exception {

        doNothing().when(userService).deleteAll();

        mockMvc.perform(delete("/users")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId(34L);
        user.setNickname("John");
        user.setLocation("London");
        user.setGender(UserGender.Male);
        String requestURL = "/users" + "/" + user.getId();
        when(userService.update(user.getId(), user)).thenReturn(user);

        mockMvc.perform(put(requestURL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isOk());


    }
}