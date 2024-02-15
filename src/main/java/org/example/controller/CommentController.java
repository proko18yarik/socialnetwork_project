package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.converter.CommentConverter;
import org.example.dto.CommentDTO;
import org.example.entity.Comment;
import org.example.exceptions.CommentException;
import org.example.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentConverter commentConverter;
    private final CommentService commentService;

    @PostMapping
    public Comment save(@RequestBody CommentDTO commentDTO) throws CommentException {
        Comment comment = commentConverter.toModel(commentDTO);
        return commentService.save(comment);
    }

    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public Comment getById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentConverter.toModel(commentDTO);
        return commentService.update(id, comment);
    }
}
