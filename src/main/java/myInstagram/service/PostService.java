package myInstagram.service;

import myInstagram.entities.Post;
import myInstagram.entities.User;

import java.util.List;

public interface PostService {
    public void save(Post post);
    Post getPostById(Long id);
    public List<Post> getAllPosts();
    public List<Post> getPostsByAuthor(String author);
    public void delete(Post post);
}
