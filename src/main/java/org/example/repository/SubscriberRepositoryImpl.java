package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SubscriberRepositoryImpl implements SubscriberCustomRepository {
    private final EntityManager entityManager;

    @Override
    public List<Object[]> getAllNicknamesAndUsersId() {
        String query = "select h.user_nickname, h.user_id from  Subscriber h";
        Query jpqlQuery = entityManager.createQuery(query);
        return jpqlQuery.getResultList();
    }

}
