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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    Long id;
    @Column(unique = true, nullable = false)
    String userName;
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
    @OneToOne (cascade = {ALL}, fetch = FetchType.EAGER)
    UserInfo userInfo;
    @OneToOne(cascade = {ALL},fetch = FetchType.EAGER)
    Follower follower;
    @OneToMany(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH}, fetch = FetchType.EAGER)
    List<Image> images;

    public User(String userName, String password, String email, String phone) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
