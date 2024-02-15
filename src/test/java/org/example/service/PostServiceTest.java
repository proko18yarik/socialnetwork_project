package org.example.service;


import org.example.entity.Post;
import org.example.repository.PostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private BeanUtilService beanUtilService;
    @InjectMocks
    private PostService postService;


    @Test
    public void testSavePost() throws Exception {

        Post post = new Post();
        post.setDescription("user123");
        post.setUser_id(15L);

        when(postRepository.save(post)).thenReturn(post);


        Post savedPost = postService.save(post);


        assertEquals(post, savedPost);
    }

    @Test
    public void testGetAll() {

        List<Post> expectedPosts = new ArrayList<>();


        List<Post> actualPosts = postService.getAll();


        assertEquals(expectedPosts, actualPosts);

    }

    @Test
    public void testFindPostById() {

        Long postId = 1L;
        Post mockPost = new Post();
        mockPost.setId(postId);
        mockPost.setDescription("jhg");
        mockPost.setUser_id(1L);


        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));


        Post foundPost = postService.findById(postId);


        Assert.assertEquals(mockPost, foundPost);
    }

    @Test
    public void testDeleteById() throws Exception {
        Post post = new Post();
        post.setDescription("hh");
        post.setUser_id(21L);
        when(postRepository.save(post)).thenReturn(post);

        postService.save(post);


        Long postId = post.getId();


        postService.deleteById(postId);

        Optional<Post> deletedPost = postRepository.findById(postId);
        assertFalse(deletedPost.isPresent());
    }

    @Test
    public void testUpdateUser() {
        Long postId = 1L;
        Post existingPost = new Post();
        Post updatedPost = new Post();
        existingPost.setId(postId);
        existingPost.setDescription("John");
        existingPost.setUser_id(1L);
        updatedPost.setId(postId);
        updatedPost.setDescription("Jane");
        updatedPost.setUser_id(1L);

        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
        Mockito.when(postRepository.save(existingPost)).thenReturn(updatedPost);


        Post result = postService.update(postId, updatedPost);


        Assert.assertEquals(updatedPost, result);
    }
}
