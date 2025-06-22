package myInstagram.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_gen")
    @SequenceGenerator(name = "like_gen", sequenceName = "like_seq")
    Long id;
    boolean isLikes;
    @OneToOne(cascade = {MERGE, REFRESH, DETACH})
    User user;
    @ManyToOne (cascade = {MERGE, REFRESH, DETACH})
    Post post;
    @ManyToOne (cascade = {MERGE, REFRESH, DETACH})
    Comment comment;
}
