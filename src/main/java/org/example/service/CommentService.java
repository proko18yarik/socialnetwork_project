package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Comment;
import org.example.exceptions.CommentException;
import org.example.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BeanUtilService beanUtilService;

    public Comment save(Comment comment) throws CommentException {
        if (comment.getUser_nickname() != null
                && comment.getPost_id() != null
                && comment.getText() != null) {
            return commentRepository.save(comment);
        } else throw new CommentException("");
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    public Comment update(Long id, Comment comment) {
        Comment existing = getById(id);
        beanUtilService.copyProperties(comment, existing);
        return commentRepository.save(existing);

    }
}

