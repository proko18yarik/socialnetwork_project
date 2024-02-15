package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Post;
import org.example.exceptions.PostException;
import org.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BeanUtilService beanUtilService;


    public Post save(Post post) throws PostException {
        if (post.getUser_id() != (null) && post.getDescription() != null) {
            return postRepository.save(post);
        } else throw new PostException("Make sure that all required fields are filled in!");
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);

    }

    public Post update(Long id, Post post) {
        Post existing = findById(id);
        beanUtilService.copyProperties(post, existing);
        return postRepository.save(existing);
    }
}
