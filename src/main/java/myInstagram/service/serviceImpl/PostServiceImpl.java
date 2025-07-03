package myInstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myInstagram.entities.Post;
import myInstagram.entities.User;
import myInstagram.repositories.PostRepo;
import myInstagram.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;

    @Override
    public void save(Post post) {
        postRepo.save(post);
    }

    @Transactional
    @Override
    public Post getPostById(Long id) {
        return postRepo.getPostById(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.getAllPosts();
    }

    @Override
    public List<Post> getPostsByAuthor(String author) {
        return postRepo.getPostsByAuthor(author);
    }

    @Override
    public void delete(Post post) {
        postRepo.delete(post);
    }
}
