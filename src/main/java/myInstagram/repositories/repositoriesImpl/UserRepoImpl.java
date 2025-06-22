package myInstagram.repositories.repositoriesImpl;

import jakarta.persistence.EntityManager;
import myInstagram.entities.User;
import myInstagram.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {
    @Autowired
    private final EntityManager entityManager;

    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUserById(Long id, User user) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
