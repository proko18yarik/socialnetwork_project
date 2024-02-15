package org.example.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubscriberCustomRepositoryTest {
    @Autowired
    @Qualifier("subscriberRepositoryImpl")
    private SubscriberCustomRepository subscriberCustomRepository;

    @Test
    public void testGetAllNicknamesAndUsersId() {
        List<Object[]> result = subscriberCustomRepository.getAllNicknamesAndUsersId();

        Assertions.assertNotNull(result);


    }
}
