package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeCustomRepository {
    List<String> findAllNicknames();
}
