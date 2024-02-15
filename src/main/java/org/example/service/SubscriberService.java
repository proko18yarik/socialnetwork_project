package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Subscriber;
import org.example.repository.SubscriberRepository;
import org.example.repository.UserRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final SubscriberDuplicateService subscriberDuplicateService;


    public Subscriber save(Subscriber subscriber) throws Exception {
        if (subscriber.getUser_nickname() != null
                && subscriber.getUser_id() != null
                && userRepositoryImpl.findAllNicknames().contains(subscriber.getUser_nickname())) {

            subscriberDuplicateService.findSubscriberDuplicate(subscriber);

            return subscriberRepository.save(subscriber);


        } else throw new Exception("Make sure that all required fields are filled in and username exists!");
    }

    public List<Subscriber> getAll() {
        return subscriberRepository.findAll();
    }


    public void deleteById(Long id) {
        subscriberRepository.deleteById(id);
    }
}
