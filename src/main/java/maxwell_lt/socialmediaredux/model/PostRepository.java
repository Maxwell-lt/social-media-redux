package maxwell_lt.socialmediaredux.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Collection<Post> findByUser(User user);
}
