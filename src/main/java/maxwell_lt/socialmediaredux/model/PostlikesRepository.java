package maxwell_lt.socialmediaredux.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostlikesRepository extends CrudRepository<Postlikes, Long> {
    Collection<Postlikes> findAllByUser(User user);

    Collection<Postlikes> findAllByPost(Post post);

    @Query("select sum(pl.likesUsed) from Postlikes pl where pl.post = :post")
    int findTotalLikesByPost(@Param("post") Post post);
}
