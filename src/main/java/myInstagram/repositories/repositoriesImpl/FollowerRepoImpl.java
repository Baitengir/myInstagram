package myInstagram.repositories.repositoriesImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Follower;
import myInstagram.entities.User;
import myInstagram.exceptions.InvalidValueException;
import myInstagram.repositories.FollowerRepo;
import myInstagram.repositories.UserRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Transactional
@RequiredArgsConstructor
public class FollowerRepoImpl implements FollowerRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    private final UserRepo userRepo;

    @Override
    public List<User> getSubscribersOrSubscriptionsByFollowerId(Long id, String subscribersOrSubscriptions) {
        Follower follower = entityManager.find(Follower.class, id);
        if (follower == null){
            throw new NoSuchElementException("\"Follower not found with id: \" + id");
        }
        List<Long> IDs ;

        if (subscribersOrSubscriptions.equalsIgnoreCase("subscriptions")){
            IDs = follower.getSubscriptions();
        } else if (subscribersOrSubscriptions.equalsIgnoreCase("subscribers")){
            IDs = follower.getSubscribers();
        } else {
            throw new InvalidValueException("Invalid value!");
        }

        List<User> users = new ArrayList<>();
        for (Long userId : IDs) {
            users.add(userRepo.getUserById(userId));
        }
        return users;
    }
}
