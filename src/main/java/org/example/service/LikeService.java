package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Like;
import org.example.exceptions.LikeException;
import org.example.repository.LikeRepository;
import org.example.repository.LikeRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeRepositoryImpl likeRepositoryImpl;
    private final UserRepositoryImpl userRepositoryImpl;

    public Like save(Like like) throws LikeException {
        if (like.getPost_id() != null
                && like.getUser_nickname() != null
                && !likeRepositoryImpl.findAllNicknames().contains(like.getUser_nickname())
                && userRepositoryImpl.findAllNicknames().contains(like.getUser_nickname())) {
            return likeRepository.save(like);
        } else
            throw new LikeException("Make sure that all required fields are filled in, absence of duplicates and username exists!");
    }

    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }
}
