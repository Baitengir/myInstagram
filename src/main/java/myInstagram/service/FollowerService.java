package myInstagram.service;

import myInstagram.entities.User;

import java.util.List;

public interface FollowerService {
    List<User> getSubscribersOrSubscriptionsByFollowerId (Long id, String subscribersOrSubscriptions);

}
