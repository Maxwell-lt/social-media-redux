package maxwell_lt.socialmediaredux.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    List<User> users;

    static Post buildDummyPost(String title, User user) {
        return Post.builder()
                .title(title)
                .user(user)
                .timestamp(Timestamp.from(Instant.now()))
                .deleted(false)
                .build();
    }

    static List<Post> buildDummyPostListWithOneUser(User user) {
        return Stream.of(
                buildDummyPost("First post!!", user),
                buildDummyPost("Did someone say post?", user))
                .collect(Collectors.toList());
    }

    static List<Post> buildDummyPostListWithMultipleUsers(User userA, User userB) {
        return Stream.of(
                buildDummyPost("First post!!", userA),
                buildDummyPost("Did someone say post?", userB))
                .collect(Collectors.toList());
    }

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
        Post post = buildDummyPost("First post!!", users.get(0));
        postRepository.save(post);
        assertThat(postRepository.findAll()).containsOnly(post);
    }

    @Test
    void canGetPostsByUser() {
        List<Post> posts = buildDummyPostListWithOneUser(users.get(0));
        postRepository.saveAll(posts);
        assertThat(postRepository.findByUser(users.get(0)))
                .containsExactlyInAnyOrderElementsOf(posts);
    }

    @Test
    void canGetOnlyPostsByUser() {
        List<Post> posts = buildDummyPostListWithMultipleUsers(users.get(0), users.get(1));
        postRepository.saveAll(posts);
        assertThat(postRepository.findByUser(users.get(0))).hasSize(1).containsOnly(posts.get(0));
    }

    @Test
    void shouldGetNoPostsByNonPostingUser() {
        List<Post> posts = buildDummyPostListWithOneUser(users.get(0));
        postRepository.saveAll(posts);
        assertThat(postRepository.findByUser(users.get(1))).isEmpty();
    }
}
