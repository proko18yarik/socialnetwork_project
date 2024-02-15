package org.example.repository;

import org.example.entity.Like;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LikeRepositoryTest {
    @Autowired
    LikeRepository likeRepository;

    @Test
    public void saveTest() {
        Like like = new Like();
        like.setUser_nickname("Amelie");
        like.setPost_id(70L);
        Like savedLike = likeRepository.save(like);
        assertNotNull(savedLike.getId());
        assertEquals(like.getUser_nickname(), savedLike.getUser_nickname());
        assertEquals(like.getPost_id(), savedLike.getPost_id());
    }

    @Test
    public void findLikeByIdTest() {
        Like like = new Like();
        like.setUser_nickname("Amelie");
        like.setPost_id(70L);
        Like savedLike = likeRepository.save(like);
        Like foundedLike = likeRepository.findById(savedLike.getId()).orElse(null);
        assertNotNull(foundedLike);
        assertEquals(savedLike.getId(), foundedLike.getId());
        assertEquals(savedLike.getUser_nickname(), foundedLike.getUser_nickname());
        assertEquals(savedLike.getPost_id(), foundedLike.getPost_id());
    }

    @Test
    public void findAllTest() {
        Like like = new Like();
        like.setUser_nickname("Amelie");
        like.setPost_id(70L);
        likeRepository.save(like);

        Like like2 = new Like();
        like2.setUser_nickname("Amelie");
        like2.setPost_id(78L);
        likeRepository.save(like2);
        List<Like> likes = likeRepository.findAll();
        assertEquals(2, likes.size());
        assertTrue(likes.contains(like));
        assertTrue(likes.contains(like2));
    }

    @Test
    public void testDeleteAllLikes() {
        Like like = new Like();
        like.setUser_nickname("Amelie");
        like.setPost_id(70L);
        likeRepository.save(like);

        Like like2 = new Like();
        like2.setUser_nickname("Amelie");
        like2.setPost_id(78L);
        likeRepository.save(like2);

        likeRepository.deleteAll();

        List<Like> likeList = likeRepository.findAll();

        assertEquals(0, likeList.size());
    }

    @Test
    public void testDeleteLikeById() {
        Like like = new Like();
        like.setUser_nickname("Amelie");
        like.setPost_id(70L);
        Like savedLike = likeRepository.save(like);


        Long likeId = savedLike.getId();
        likeRepository.deleteById(likeId);

        assertFalse(likeRepository.existsById(likeId));
    }

}




