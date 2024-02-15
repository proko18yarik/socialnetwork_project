package org.example.converter;

import org.example.dto.SubscribersDTO;
import org.example.entity.Subscriber;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriberConverterTest {

    @Test
    public void testToModel() {
        SubscribersDTO subscribersDTO = new SubscribersDTO("John", 123L);


        SubscriberConverter subscriberConverter = new SubscriberConverter();
        Subscriber subscriber = subscriberConverter.toModel(subscribersDTO);


        assertEquals("John", subscriber.getUser_nickname());
        assertEquals(123, subscriber.getUser_id());

    }
}
