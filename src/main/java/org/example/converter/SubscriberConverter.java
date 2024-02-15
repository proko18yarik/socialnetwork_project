package org.example.converter;

import lombok.AllArgsConstructor;
import org.example.dto.SubscribersDTO;
import org.example.entity.Subscriber;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriberConverter {
    public Subscriber toModel(SubscribersDTO subscribersDTO) {
        return Subscriber.builder().
                user_nickname(subscribersDTO.user_nickname()).
                user_id(subscribersDTO.user_id()).
                build();
    }
}
