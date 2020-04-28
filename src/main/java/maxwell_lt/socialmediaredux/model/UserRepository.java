package maxwell_lt.socialmediaredux.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String testuser);

    Optional<User> findByEmail(String s);
}
