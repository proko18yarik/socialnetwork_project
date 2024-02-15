package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor

public class UserRepositoryImpl implements UserCustomRepository {

    private final EntityManager entityManager;


    @Override
    public List<String> findAllNicknames() {
        String query =
                "select s.nickname from User s ";
        Query jpqlQuery = entityManager.createQuery(query);
        return jpqlQuery.getResultList();
    }


}
