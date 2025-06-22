package myInstagram.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_gen")
    @SequenceGenerator(name = "comment_gen", sequenceName = "comment_seq")
    Long id;
    String comment;
    int likesCount = 0;
    LocalDateTime createdAt;
    @OneToOne (cascade = {MERGE, REFRESH, DETACH})
    User user;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    Post post;
    @OneToMany(mappedBy = "comment", cascade = {MERGE, REFRESH, REMOVE, PERSIST})
    List<Like> likes;
}
