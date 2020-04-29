package maxwell_lt.socialmediaredux.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostlikesRepositoryTest {

    @Autowired
    PostlikesRepository postlikesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    User user;
    Post post;

    static Postlikes buildDummyPostlikes(Post post, User user) {
        return Postlikes.builder().post(post).user(user).likesUsed(15).build();
    }

    @BeforeEach
    void setUp() {
        user = userRepository.save(UserRepositoryTest.buildDummyUser());
        post = postRepository.save(PostRepositoryTest.buildDummyPost("Test post.", user));
    }

    @AfterEach
    void tearDown() {
        postlikesRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void canSaveAndRetrievePostlikes() {
        Postlikes postlikes = buildDummyPostlikes(post, user);
        postlikesRepository.save(postlikes);
        assertThat(postlikesRepository.findAll()).containsOnly(postlikes);
    }

    @Test
    void canGetPostlikesByUser() {
        Postlikes postlikes = buildDummyPostlikes(post, user);
        postlikesRepository.save(postlikes);
        assertThat(postlikesRepository.findAllByUser(user)).containsOnly(postlikes);
    }

    @Test
    void canGetPostlikesByPost() {
        Postlikes postlikes = buildDummyPostlikes(post, user);
        postlikesRepository.save(postlikes);
        assertThat(postlikesRepository.findAllByPost(post)).containsOnly(postlikes);
    }

    @Test
    void canGetTotalLikesByPost() {
        User user2 = userRepository.save(UserRepositoryTest.buildDummyUser("another", "two@ex.co"));
        postlikesRepository.save(buildDummyPostlikes(post, user));
        postlikesRepository.save(buildDummyPostlikes(post, user2));
        assertThat(postlikesRepository.findAllByPost(post)).hasSize(2);
        assertThat(postlikesRepository.findTotalLikesByPost(post)).isEqualTo(30);
    }
}
