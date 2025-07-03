package myInstagram.repositories.repositoriesImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Image;
import myInstagram.entities.Post;
import myInstagram.entities.User;
import myInstagram.repositories.ImageRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ImageRepoImpl implements ImageRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Image image) {
        entityManager.persist(image);
    }

    @Override
    public Image getImageByPost(Post post) {
        return entityManager.createQuery("select i from Image i where i.post = :post", Image.class)
                .setParameter("post", post)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Image> getImagesByPost(Post post) {
        return entityManager.createQuery("select i from Image i where i.post = :post", Image.class)
                .getResultList();
    }
}
