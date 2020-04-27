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
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "purchase")
public class Purchase {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        int id;

        @Column(name = "pricePaid", nullable = false, precision = 4)
        BigDecimal pricePaid;

        @Column(name = "likesBought", nullable = false)
        int likesBought;

        @ManyToOne
        @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
        User user;

        @Column(name = "timestamp", nullable = false)
        Timestamp timestamp;
}
