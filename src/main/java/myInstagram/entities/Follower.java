package myInstagram.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follower_gen")
    @SequenceGenerator(name = "follower_gen", sequenceName = "follower_seq")
    Long id;
    List<Long> subscribers;
    List<Long> subscriptions;
    @OneToOne (mappedBy = "follower", cascade = {DETACH, REFRESH, MERGE})
    User user;
}
