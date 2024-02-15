package org.example.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserCustomRepositoryTest {
    @Mock
    private UserCustomRepository userCustomRepository;

    @Test
    public void testFindAllNicknames() {
        List<String> expectedNicknames = new ArrayList<>();
        expectedNicknames.add("Amelie");
        expectedNicknames.add("John");

        when(userCustomRepository.findAllNicknames()).thenReturn(expectedNicknames);

        List<String> actualNicknames = userCustomRepository.findAllNicknames();

        assertEquals(expectedNicknames, actualNicknames);
    }
}

