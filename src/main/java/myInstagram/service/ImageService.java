package myInstagram.service;

import myInstagram.entities.Image;
import myInstagram.entities.Post;

import java.util.List;

public interface ImageService {
    void save(Image image);
    Image getImageByPost(Post post);
    List <Image> getImagesByPost(Post post);
}
