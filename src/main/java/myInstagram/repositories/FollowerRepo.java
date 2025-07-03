package myInstagram.repositories;

import myInstagram.entities.Follower;
import myInstagram.entities.User;

import java.util.List;

public interface FollowerRepo {
    List<User> getSubscribersOrSubscriptionsByFollowerId (Long id, String subscribersOrSubscriptions);

}
