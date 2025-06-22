package myInstagram.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name = "post_gen", sequenceName = "post_seq")
    Long id;
    String title;
    String description;
    int likesCount = 0;
    LocalDate createdAt;
    @ManyToOne (cascade = {REFRESH, MERGE, DETACH})
    User user;
    @OneToMany(mappedBy = "post", cascade = {REFRESH, MERGE, REMOVE})
    List<Comment> comments;
    @OneToMany(mappedBy = "post", cascade = {REFRESH, MERGE, REMOVE, PERSIST})
    List<Like> likes;
    @OneToMany(mappedBy = "post", cascade = {REFRESH, MERGE, REMOVE,})
    List<Image> images;
}
