package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.entity.Subscriber;
import org.example.repository.SubscriberRepository;
import org.example.repository.SubscriberRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {

    @Mock
    private SubscriberDuplicateService subscriberDuplicateService;
    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private SubscriberRepositoryImpl subscriberRepositoryImpl;
    @Mock
    private UserRepositoryImpl userRepositoryImpl;
    @InjectMocks
    private SubscriberService subscriberService;


    @Test
    public void testSaveSubscriber() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("user123");
        subscriber.setUser_id(1L);

        when(userRepositoryImpl.findAllNicknames()).thenReturn(List.of("user123"));
        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);

        Subscriber savedSubscriber = subscriberService.save(subscriber);

        // Assert
        assertEquals(subscriber, savedSubscriber);
        verify(subscriberDuplicateService, times(1)).findSubscriberDuplicate(subscriber);
    }


    @Test
    public void testGetAll() {

        List<Subscriber> expectedSubscribers = new ArrayList<>();


        List<Subscriber> actualSubscribers = subscriberService.getAll();


        assertEquals(expectedSubscribers, actualSubscribers);

    }

    @Test
    public void testDeleteById() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("John");
        subscriber.setUser_id(21L);
        when(userRepositoryImpl.findAllNicknames()).thenReturn(List.of("John"));
        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);

        subscriberService.save(subscriber);


        Long subscriberId = subscriber.getId();


        subscriberService.deleteById(subscriberId);

        Optional<Subscriber> deletedUser = subscriberRepository.findById(subscriberId);
        assertFalse(deletedUser.isPresent());
    }
}

