package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LikeRepositoryImpl implements LikeCustomRepository {
    private final EntityManager entityManager;

    @Override
    public List<String> findAllNicknames() {
        String query = "select h.user_nickname from Like h";
        Query jpqlQuery = entityManager.createQuery(query);
        return jpqlQuery.getResultList();

    }
}
