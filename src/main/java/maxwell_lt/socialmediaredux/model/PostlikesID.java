package maxwell_lt.socialmediaredux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostlikesID implements Serializable {
    Post post;
    User user;
}
