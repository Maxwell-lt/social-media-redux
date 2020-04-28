package maxwell_lt.socialmediaredux.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    List<User> users;

    @BeforeEach
    void setUp() {
        users = (List<User>) userRepository.saveAll(UserRepositoryTest.buildDummyUserList());
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void canSaveAndRetrievePost() {
        Post post = Post.builder()
                .title("First post!!")
                .user(users.get(0))
                .timestamp(Timestamp.from(Instant.now()))
                .deleted(false)
                .build();
        postRepository.save(post);
        assertThat(postRepository.findAll())
                .containsOnly(post);
    }
}
