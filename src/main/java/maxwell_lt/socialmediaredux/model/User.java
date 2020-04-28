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
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "username", nullable = false, length = 50)
    String username;

    @Column(name = "password", nullable = false, length = 60)
    String password;

    @Column(name = "email", nullable = false, length = 254)
    String email;

    @Column(name = "creationDate", nullable = false)
    Timestamp creationDate;

    @Column(name = "currentLikes", nullable = false, precision = 2)
    BigDecimal currentLikes;

    @Column(name = "hasPublicLikes", nullable = false)
    boolean publicLikes;

    @Column(name = "hasAdminPermissions", nullable = false)
    boolean adminPermissions;

    @Column(name = "hasModeratorPermissions", nullable = false)
    boolean moderatorPermissions;

    @Column(name = "isDeleted", nullable = false)
    boolean deleted;
}
