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
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    private List<User> users;
    private List<Post> posts;

    static Comment buildDummyComment(String text, User user, Post post) {
        return Comment.builder()
                .text(text)
                .user(user)
                .post(post)
                .timestamp(Timestamp.from(Instant.now()))
                .deleted(false)
                .build();
    }

    static List<Comment> buildDummyCommentList(User user, Post post) {
        return Stream.of(
                buildDummyComment("What did you just say about me?", user, post),
                buildDummyComment("^This^", user, post),
                buildDummyComment("Not today, old friend.", user, post))
                .collect(Collectors.toList());
    }

    static List<Comment> buildDummyCommentListWithVaryingFKs(
            User userA, User userB, Post postA, Post postB) {
        return Stream.of(
                buildDummyComment("What did you just say about me?", userA, postA),
                buildDummyComment("^This^", userB, postA),
                buildDummyComment("Not today, old friend.", userA, postB))
                .collect(Collectors.toList());
    }

    @BeforeEach
    void setUp() {
        users = (List<User>) userRepository.saveAll(UserRepositoryTest.buildDummyUserList());
        posts =
                (List<Post>)
                        postRepository.saveAll(
                                PostRepositoryTest.buildDummyPostListWithMultipleUsers(
                                        users.get(0), users.get(1)));
    }

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void canSaveAndRetrieveComment() {
        Comment comment = buildDummyComment("This is fine.", users.get(0), posts.get(0));
        commentRepository.save(comment);
        assertThat(commentRepository.findAll()).containsOnly(comment);
    }

    @Test
    void canGetAllComments() {
        List<Comment> comments = buildDummyCommentList(users.get(0), posts.get(0));
        commentRepository.saveAll(comments);
        assertThat(commentRepository.findAll()).containsExactlyInAnyOrderElementsOf(comments);
    }

    @Test
    void canGetCommentsByUser() {
        List<Comment> comments =
                buildDummyCommentListWithVaryingFKs(
                        users.get(0), users.get(1), posts.get(0), posts.get(1));
        commentRepository.saveAll(comments);
        assertThat(commentRepository.findAllByUser(users.get(0)))
                .containsExactlyInAnyOrder(comments.get(0), comments.get(2));
    }

    @Test
    void canGetCommentsByPost() {
        List<Comment> comments =
                buildDummyCommentListWithVaryingFKs(
                        users.get(0), users.get(1), posts.get(0), posts.get(1));
        commentRepository.saveAll(comments);
        assertThat(commentRepository.findAllByPost(posts.get(0)))
                .containsExactlyInAnyOrder(comments.get(0), comments.get(1));
    }
}
