package maxwell_lt.socialmediaredux.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void userRepositoryCanSaveAndRetrieve() {
        User user = buildDummyUser();
        userRepository.save(user);
        assertThat(userRepository.findAll())
                .containsOnly(user);
    }

    @Test
    void canGetUserByUsername() {
        List<User> users = buildDummyUserList();
        userRepository.saveAll(users);
        assertThat(userRepository.findByUsername("testuser"))
                .isNotEmpty()
                .contains(users.get(0));
    }

    @Test
    void canGetUserByEmail() {
        List<User> users = buildDummyUserList();
        userRepository.saveAll(users);
        assertThat(userRepository.findByEmail("email@e.mail"))
                .isNotEmpty()
                .contains(users.get(2));
    }

    User buildDummyUser() {
        return buildDummyUser("testuser", "testuser@example.com");
    }

    User buildDummyUser(String username, String email) {
        return User.builder()
                .username(username)
                .email(email)
                .password("p@ssw0rd")
                .creationDate(Timestamp.from(Instant.now()))
                .currentLikes(BigDecimal.TEN)
                .publicLikes(true)
                .adminPermissions(false)
                .moderatorPermissions(false)
                .deleted(false)
                .build();
    }

    List<User> buildDummyUserList() {
        return Stream.of(
                buildDummyUser("testuser", "testuser@example.com"),
                buildDummyUser("helloworld", "hw@example.com"),
                buildDummyUser("user_name", "email@e.mail"))
                .collect(Collectors.toList());
    }
}