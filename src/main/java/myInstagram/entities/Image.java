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
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_gen")
    @SequenceGenerator(name = "image_gen", sequenceName = "image_seq")
    Long id;
    String url;
    @ManyToOne(cascade = {DETACH,REFRESH, MERGE})
    User user;
    @ManyToOne(cascade = {DETACH,REFRESH, MERGE})
    Post post;

}
