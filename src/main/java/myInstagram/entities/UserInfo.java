package myInstagram.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import myInstagram.enums.Gender;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_informations")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userInfo_gen")
    @SequenceGenerator(name = "userInfo_gen", sequenceName = "userInfo_seq")
    Long id;
    String fullName;
    @Column(columnDefinition = "TEXT")
    String biography;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String image;
    @OneToOne(mappedBy = "userInfo", cascade = {REFRESH, MERGE, DETACH})
    User user;
}
