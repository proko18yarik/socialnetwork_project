package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.converter.PostConverter;
import org.example.dto.PostDTO;
import org.example.entity.Post;
import org.example.exceptions.PostException;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostConverter postConverter;
    private final PostService postService;

    @PostMapping
    public Post save(@RequestBody PostDTO postDTO) throws PostException {
        Post post = postConverter.toModel(postDTO);
        return postService.save(post);
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        Post post = postConverter.toModel(postDTO);
        return postService.update(id, post);
    }
}
