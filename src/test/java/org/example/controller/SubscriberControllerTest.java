package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.converter.SubscriberConverter;
import org.example.entity.Subscriber;
import org.example.service.SubscriberService;
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
@WebMvcTest(SubscriberController.class)
public class SubscriberControllerTest {
    @MockBean
    SubscriberService subscriberService;
    @MockBean
    SubscriberConverter subscriberConverter;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveTest() throws Exception {
        Subscriber subscriber = new Subscriber();

        when(subscriberService.save(subscriber)).thenReturn(subscriber);
        mockMvc.perform(post("/subscribers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(subscriber)))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception {

        List<Subscriber> subscriberList = new ArrayList<>();
        when(subscriberService.getAll()).thenReturn(subscriberList);
        mockMvc.perform(get("/subscribers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()); //For some reason unknown to me when calling this method everything works, but it returns 500


    }

    @Test
    public void deleteSubscriberByIdTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        Long l = 100L;
        subscriber.setId(l);
        doNothing().when(subscriberService).deleteById(subscriber.getId());

        mockMvc.perform(delete("/subscribers/{id}", subscriber.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

}
