package myInstagram.repositories.repositoriesImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Follower;
import myInstagram.entities.User;
import myInstagram.repositories.UserRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUserById(Long id, User user) {
        User userInDb = entityManager.find(User.class, id);
        if (userInDb != null) {
            if (user.getUserName() != null) {
                userInDb.setUserName(user.getUserName());
            }
            if (user.getEmail() != null) {
                userInDb.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                userInDb.setPassword(user.getPassword());
            }
            if (user.getPhone() != null) {
                userInDb.setPhone(user.getPhone());
            }
        } else {
            System.out.println("User not found with id " + id);
        }
    }

    @Override
    public User getUserByName(String userName) {
        return entityManager.createQuery("select u from User u where u.userName = :userName", User.class)
                .setParameter("userName", userName)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByPhone(String phone) {
        return entityManager.createQuery("select u from User u where u.phone = :phone", User.class)
                .setParameter("phone", phone)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return user;
        } else {
            System.out.println("User not found with id " + id);
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            System.out.println("User not found with id " + id);
        }
    }

//    @Override
//    public List<User> getSubscribersByUserId(Long id) {
//
//    }
}
