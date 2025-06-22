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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    Long id;
    @Column(unique = true, nullable = false)
    String UserName;
    String password;
    @Column(unique = true, nullable = false)
    String email;
    @Column(unique = true, nullable = false)
    String phone;
    @OneToMany(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH}, fetch = FetchType.EAGER)
    List<Post> posts;
    @OneToOne(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH})
    Comment comment;
    @OneToOne(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH})
    Like like;
    @OneToOne (cascade = {REMOVE, MERGE, REFRESH, PERSIST})
    UserInfo userInfo;
    @OneToOne(cascade = {REMOVE, MERGE, REFRESH, PERSIST})
    Follower follower;
    @OneToMany(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH})
    List<Image> images;
}
