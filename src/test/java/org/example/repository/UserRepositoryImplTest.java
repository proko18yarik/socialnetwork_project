package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    public void testFindAllNicknames() {
        List<String> expectedNicknames = new ArrayList<>();
        expectedNicknames.add("Amelie");
        expectedNicknames.add("John");

        String jpqlQuery = "select s.nickname from User s ";
        when(entityManager.createQuery(jpqlQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedNicknames);

        List<String> actualNicknames = userRepository.findAllNicknames();

        assertEquals(expectedNicknames, actualNicknames);
    }
}
