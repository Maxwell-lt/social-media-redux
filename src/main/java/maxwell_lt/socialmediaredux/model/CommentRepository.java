package maxwell_lt.socialmediaredux.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Collection<Comment> findAllByUser(User user);

    Collection<Comment> findAllByPost(Post post);
}
