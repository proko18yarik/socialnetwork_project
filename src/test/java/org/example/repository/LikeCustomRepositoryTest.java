package org.example.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LikeCustomRepositoryTest {
    @Autowired
    @Qualifier("likeRepositoryImpl")
    private LikeCustomRepository likeCustomRepository;

    @Test
    public void testFindAllNicknames() {

        List<String> nicknames = likeCustomRepository.findAllNicknames();


        assertThat(nicknames).isNotNull();
        assertThat(nicknames).hasSize(likeCustomRepository.findAllNicknames().size());
    }
}
