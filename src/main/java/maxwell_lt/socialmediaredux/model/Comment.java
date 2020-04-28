package maxwell_lt.socialmediaredux.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "imageId", length = 36)
    String imageId;

    @Column(name = "text", nullable = false, length = 5000)
    String text;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "post", referencedColumnName = "id", nullable = false)
    Post post;

    @Column(name = "timestamp", nullable = false)
    Timestamp timestamp;

    @Column(name = "deleted", nullable = false)
    boolean deleted;
}
