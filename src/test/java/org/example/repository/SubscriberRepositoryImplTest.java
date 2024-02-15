package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubscriberRepositoryImplTest {

    @Test
    public void testGetAllNicknamesAndUsersId() {

        EntityManager entityManager = mock(EntityManager.class);
        SubscriberRepositoryImpl repository = new SubscriberRepositoryImpl(entityManager);
        Query query = mock(Query.class);
        List<Object[]> expectedResult = new ArrayList<>();

        Object[] data1 = {"Нікнейм1", 1};
        Object[] data2 = {"Нікнейм2", 2};
        expectedResult.add(data1);
        expectedResult.add(data2);

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedResult);


        List<Object[]> actualResult = repository.getAllNicknamesAndUsersId();


        assertEquals(expectedResult, actualResult);
        verify(entityManager).createQuery(anyString());
        verify(query).getResultList();
    }

}



