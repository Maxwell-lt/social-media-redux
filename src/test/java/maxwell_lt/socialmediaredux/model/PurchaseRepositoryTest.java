package maxwell_lt.socialmediaredux.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PurchaseRepositoryTest {
    @Autowired PurchaseRepository purchaseRepository;
    @Autowired UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(UserRepositoryTest.buildDummyUser());
    }

    @AfterEach
    void tearDown() {
        purchaseRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void canSaveAndRetrievePurchase() {
        Purchase purchase =
                Purchase.builder()
                        .user(user)
                        .likesBought(2750)
                        .pricePaid(new BigDecimal("24.9900"))
                        .timestamp(Timestamp.from(Instant.now()))
                        .build();
        purchaseRepository.save(purchase);
        assertThat(purchaseRepository.findAll()).containsOnly(purchase);
    }
}
