package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Subscriber;
import org.example.exceptions.SubscriberDuplicateException;
import org.example.repository.SubscriberRepositoryImpl;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class SubscriberDuplicateService {
    private final SubscriberRepositoryImpl subscriberRepositoryImpl;

    public void findSubscriberDuplicate(Subscriber subscriber) throws Exception {

        List<Object[]> results = subscriberRepositoryImpl.getAllNicknamesAndUsersId();
        for (Object[] row : results) {
            String nickname = (String) row[0];
            Long userId = (Long) row[1];
            String userIdtoString = userId.toString();
            String s = nickname + userIdtoString;
            String s2 = subscriber.getUser_id().toString();
            String s3 = subscriber.getUser_nickname() + s2;
            if (s.equals(s3)) {
                throw new SubscriberDuplicateException("Subscription has already been done!");
            }
        }

    }
}
