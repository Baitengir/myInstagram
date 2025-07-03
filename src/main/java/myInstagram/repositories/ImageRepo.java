package myInstagram.repositories;

import myInstagram.entities.Image;
import myInstagram.entities.Post;
import myInstagram.entities.User;

import java.util.List;

public interface ImageRepo {
    void save(Image image);
    Image getImageByPost(Post post);
    List<Image> getImagesByPost(Post post);
}
