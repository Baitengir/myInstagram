package myInstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myInstagram.entities.Image;
import myInstagram.entities.Post;
import myInstagram.repositories.ImageRepo;
import myInstagram.service.ImageService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepo imageRepo;

    @Override
    public void save(Image image) {
        imageRepo.save(image);
    }

    @Override
    public Image getImageByPost(Post post) {
        return imageRepo.getImageByPost(post);
    }

    @Override
    public List<Image> getImagesByPost(Post post) {
        return imageRepo.getImagesByPost(post);
    }
}
