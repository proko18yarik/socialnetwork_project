package org.example.service;

import org.example.entity.Like;
import org.example.exceptions.LikeException;
import org.example.repository.LikeRepository;
import org.example.repository.LikeRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LikeServiceTest {
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private LikeRepositoryImpl likeRepositoryImpl;
    @Mock
    private UserRepositoryImpl userRepositoryImpl;
    @InjectMocks
    private LikeService likeService;


    @Test
    public void testSaveLike() throws LikeException {

        Like like = new Like();
        like.setPost_id(12345L);
        like.setUser_nickname("exampleUser");

        when(likeRepositoryImpl.findAllNicknames()).thenReturn(new ArrayList<>());
        when(userRepositoryImpl.findAllNicknames()).thenReturn(List.of("exampleUser"));
        when(likeService.save(like)).thenReturn(like);


        Like savedLike = likeService.save(like);


        assertNotNull(savedLike);
        assertEquals(like.getPost_id(), savedLike.getPost_id());
        assertEquals(like.getUser_nickname(), savedLike.getUser_nickname());
    }


    @Test
    public void testDeleteById_ValidId_CallsLikeRepositoryDeleteById() {

        Long id = 1L;

        likeService.deleteById(id);


        verify(likeRepository, times(1)).deleteById(id);
    }
}

