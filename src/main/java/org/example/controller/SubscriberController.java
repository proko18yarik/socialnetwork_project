package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.converter.SubscriberConverter;
import org.example.dto.SubscribersDTO;
import org.example.entity.Subscriber;
import org.example.entity.User;
import org.example.exceptions.SubscriberException;
import org.example.service.SubscriberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subscribers")
public class SubscriberController {
    private final SubscriberConverter subscriberConverter;
    private final SubscriberService subscriberService;

    @PostMapping
    public Subscriber save(@RequestBody SubscribersDTO subscribersDTO) throws Exception {
        Subscriber subscriber = subscriberConverter.toModel(subscribersDTO);
        return subscriberService.save(subscriber);
    }

    @GetMapping
    @ResponseStatus()
    public List<Subscriber> getAll() {
        return subscriberService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        subscriberService.deleteById(id);
    }
}
