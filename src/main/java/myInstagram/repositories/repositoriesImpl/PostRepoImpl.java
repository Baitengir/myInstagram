package myInstagram.repositories.repositoriesImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Post;
import myInstagram.entities.User;
import myInstagram.repositories.PostRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepoImpl implements PostRepo {
    private static final Log log = LogFactory.getLog(PostRepoImpl.class);
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Post post) {
        post.setCreatedAt(LocalDate.now());
        entityManager.persist(post);
    }

    @Override
    public Post getPostById(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public List<Post> getAllPosts() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public List<Post> getPostsByAuthor(String author) {
        return entityManager.createQuery("select p from Post p where p.user.userName = :author", Post.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public void delete(Post post) {
        entityManager.remove(post);
        // todo
    }
}
