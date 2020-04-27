package maxwell_lt.socialmediaredux.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "postlikes")
@IdClass(PostlikesID.class)
public class Postlikes {
    @Id
    @ManyToOne
    @JoinColumn(name = "post", referencedColumnName = "id", nullable = false)
    Post post;
    @Id
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    User user;
    @Column(name = "likesUsed", nullable = false)
    int likesUsed;
}
