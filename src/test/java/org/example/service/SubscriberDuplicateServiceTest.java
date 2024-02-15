package org.example.service;

import org.example.entity.Subscriber;
import org.example.exceptions.SubscriberDuplicateException;
import org.example.repository.SubscriberRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriberDuplicateServiceTest {

    @Mock
    private SubscriberRepositoryImpl subscriberRepositoryImpl;

    @InjectMocks
    private SubscriberDuplicateService subscriberDuplicateService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindSubscriberDuplicate_ThrowsException() {
        // Arrange
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_id(123L);
        subscriber.setUser_nickname("test");

        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"test", 123L});
        when(subscriberRepositoryImpl.getAllNicknamesAndUsersId()).thenReturn(results);

        // Act & Assert
        assertThrows(SubscriberDuplicateException.class, () -> subscriberDuplicateService.findSubscriberDuplicate(subscriber), "Підписка вже існує!");
    }

}

