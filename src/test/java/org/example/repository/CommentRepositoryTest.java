package org.example.repository;

import org.example.entity.Comment;
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
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        comment.setText("This is a test comment");

        Comment savedComment = commentRepository.save(comment);

        assertNotNull(savedComment.getId());
        assertEquals(comment.getText(), savedComment.getText());
    }

    @Test
    public void testFindCommentById() {
        Comment comment = new Comment();
        comment.setText("This is a test comment");

        Comment savedComment = commentRepository.save(comment);

        Comment foundComment = commentRepository.findById(savedComment.getId()).orElse(null);

        assertNotNull(foundComment);
        assertEquals(savedComment.getId(), foundComment.getId());
        assertEquals(savedComment.getText(), foundComment.getText());
    }

    @Test
    public void testFindAllComments() {
        Comment comment1 = new Comment();
        comment1.setText("This is comment 1");
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setText("This is comment 2");
        commentRepository.save(comment2);

        List<Comment> comments = commentRepository.findAll();

        assertEquals(2, comments.size());
        assertTrue(comments.contains(comment1));
        assertTrue(comments.contains(comment2));
    }

    @Test
    public void testDeleteAllComments() {
        Comment comment1 = new Comment();
        comment1.setText("This is comment 1");
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setText("This is comment 2");
        commentRepository.save(comment2);

        commentRepository.deleteAll();

        List<Comment> comments = commentRepository.findAll();

        assertEquals(0, comments.size());
    }

    @Test
    public void testDeleteCommentById() {
        Comment comment = new Comment();
        comment.setText("This is a comment");
        Comment savedComment = commentRepository.save(comment);

        Long commentId = savedComment.getId();
        commentRepository.deleteById(commentId);

        assertFalse(commentRepository.existsById(commentId));
    }

}
