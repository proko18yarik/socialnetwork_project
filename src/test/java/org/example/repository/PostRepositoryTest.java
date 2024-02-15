package org.example.repository;

import org.example.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void saveTest() {
        Post post = new Post();
        post.setDescription("So cute!");
        post.setUser_id(67L);
        Post savedPost = postRepository.save(post);
        assertNotNull(savedPost.getId());
        assertEquals(post.getDescription(), savedPost.getDescription());
    }

    @Test
    public void findPostByIdTest() {
        Post post = new Post();
        post.setDescription("So cute!");
        post.setUser_id(67L);
        Post savedPost = postRepository.save(post);
        Post foundedPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertNotNull(foundedPost);
        assertEquals(savedPost.getId(), foundedPost.getId());
        assertEquals(savedPost.getDescription(), foundedPost.getDescription());
        assertEquals(savedPost.getUser_id(), foundedPost.getUser_id());
    }

    @Test
    public void findAllTest() {
        Post post = new Post();
        post.setDescription("Hi");
        post.setUser_id(67L);
        postRepository.save(post);
        Post post1 = new Post();
        post1.setDescription("Buy!");
        post1.setUser_id(67L);
        postRepository.save(post1);
        List<Post> posts = postRepository.findAll();
        assertEquals(postRepository.findAll().size(), posts.size());
        assertTrue(posts.contains(post));
        assertTrue(posts.contains(post1));
    }

    @Test
    public void testDeleteAllPosts() {
        Post post = new Post();
        post.setDescription("This is comment 1");
        post.setUser_id(67L);
        postRepository.save(post);

        Post post1 = new Post();
        post1.setDescription("This is comment 2");
        post1.setUser_id(67L);
        postRepository.save(post1);

        postRepository.deleteAll();

        List<Post> postList = postRepository.findAll();

        assertEquals(0, postList.size());
    }

    @Test
    public void testDeletePostById() {
        Post post = new Post();
        post.setDescription("This is a comment");
        post.setUser_id(67L);
        Post savedPost = postRepository.save(post);

        Long postId = savedPost.getId();
        postRepository.deleteById(postId);

        assertFalse(postRepository.existsById(postId));
    }

}

