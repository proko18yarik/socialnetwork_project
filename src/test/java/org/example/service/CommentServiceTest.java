package org.example.service;

import org.example.entity.Comment;
import org.example.exceptions.CommentException;
import org.example.repository.CommentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BeanUtilService beanUtilService;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void testSave_ValidUser_SuccessfullySaved() throws CommentException {
        Comment comment = new Comment();
        comment.setText("Lola");
        comment.setUser_nickname("Amelie");
        comment.setPost_id(3L);

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment savedComment = commentService.save(comment);

        assertNotNull(savedComment);
        assertEquals(comment, savedComment);

        verify(commentRepository).save(comment);
    }

    @Test
    public void testGetAll() {

        List<Comment> expectedComment = new ArrayList<>();


        List<Comment> actualComment = commentService.getAll();


        assertEquals(expectedComment, actualComment);

    }

    @Test
    public void testDeleteById() throws CommentException {
        Comment comment = new Comment();
        comment.setText("Ki");
        comment.setUser_nickname("Amelie");
        comment.setPost_id(6L);
        commentService.save(comment);


        Long commentId = comment.getId();


        commentService.deleteById(commentId);

        Optional<Comment> deletedComment = commentRepository.findById(commentId);
        assertFalse(deletedComment.isPresent());
    }

    @Test
    public void testFindCommentById() {

        Long commentId = 1L;
        Comment mockComment = new Comment();
        mockComment.setId(commentId);


        Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.of(mockComment));


        Comment foundComment = commentService.getById(commentId);


        Assert.assertEquals(mockComment, foundComment);
    }

    @Test
    public void testUpdateComment() {
        Long commentId = 1L;
        Comment existingComment = new Comment();
        Comment updatedComment = new Comment();
        existingComment.setId(commentId);
        existingComment.setText("John");
        existingComment.setPost_id(9L);
        updatedComment.setId(commentId);
        updatedComment.setText("Jane");
        updatedComment.setPost_id(9L);

        Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        Mockito.when(commentRepository.save(existingComment)).thenReturn(updatedComment);


        Comment result = commentService.update(commentId, updatedComment);


        Assert.assertEquals(updatedComment, result);
    }
}
