package org.example.repository;

import org.example.entity.Subscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubscriberRepositoryTest {
    @Autowired
    private SubscriberRepository subscriberRepository;

    @Test
    public void testSaveComment() {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("John");
        subscriber.setUser_id(68L);

        Subscriber savedSubscriber = subscriberRepository.save(subscriber);

        assertNotNull(savedSubscriber.getId());
        assertEquals(subscriber.getUser_nickname(), savedSubscriber.getUser_nickname());
        assertEquals(subscriber.getUser_id(), savedSubscriber.getUser_id());
    }

    @Test
    public void testFindSubscriberById() {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("Amelie");
        subscriber.setUser_id(69L);

        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        Subscriber foundSubscriber = subscriberRepository.findById(savedSubscriber.getId()).orElse(null);

        assertNotNull(foundSubscriber);
        assertEquals(savedSubscriber.getUser_nickname(), foundSubscriber.getUser_nickname());
        assertEquals(savedSubscriber.getUser_id(), foundSubscriber.getUser_id());
    }

    @Test
    public void testFindAllSubscribers() {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("John");
        subscriber.setUser_id(68L);

        subscriberRepository.save(subscriber);

        Subscriber subscriber2 = new Subscriber();
        subscriber2.setUser_nickname("Amelie");
        subscriber2.setUser_id(69L);

        subscriberRepository.save(subscriber2);

        List<Subscriber> subscriberList = subscriberRepository.findAll();

        assertEquals(2, subscriberList.size());
        assertTrue(subscriberList.contains(subscriber));
        assertTrue(subscriberList.contains(subscriber2));
    }

    @Test
    public void testDeleteAllSubscribers() {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("Amelie");
        subscriber.setUser_id(69L);

        subscriberRepository.save(subscriber);

        Subscriber subscriber2 = new Subscriber();
        subscriber2.setUser_nickname("John");
        subscriber2.setUser_id(68L);

        subscriberRepository.save(subscriber2);

        subscriberRepository.deleteAll();

        List<Subscriber> subscriberList = subscriberRepository.findAll();

        assertEquals(0, subscriberList.size());
    }

    @Test
    public void testDeleteSubscriberById() {
        Subscriber subscriber = new Subscriber();
        subscriber.setUser_nickname("Amelie");
        subscriber.setUser_id(69L);

        Subscriber savedSubscriber = subscriberRepository.save(subscriber);

        Long subscriberId = savedSubscriber.getId();
        subscriberRepository.deleteById(subscriberId);

        assertFalse(subscriberRepository.existsById(subscriberId));
    }

}



