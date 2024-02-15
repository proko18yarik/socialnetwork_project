package org.example.repository;

import org.example.entity.Like;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LikeRepositoryImplTest {
    @Autowired
    private LikeRepositoryImpl likeRepositoryImpl;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindAllNicknames() {

        Like like1 = new Like();
        like1.setUser_nickname("nickname1");
        entityManager.persist(like1);

        Like like2 = new Like();
        like2.setUser_nickname("nickname2");
        entityManager.persist(like1);

        Like like3 = new Like();
        like3.setUser_nickname("nickname3");
        entityManager.persist(like1);

        List<String> nicknames = likeRepositoryImpl.findAllNicknames();


        assertThat(nicknames).isNotNull();
        assertThat(nicknames).isEqualTo(likeRepositoryImpl.findAllNicknames());
    }
}
