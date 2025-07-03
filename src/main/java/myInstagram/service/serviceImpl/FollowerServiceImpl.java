package myInstagram.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myInstagram.entities.User;
import myInstagram.repositories.FollowerRepo;
import myInstagram.service.FollowerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepo followerRepo;

    @Override
    public List<User> getSubscribersOrSubscriptionsByFollowerId(Long id, String subscribersOrSubscriptions) {
        return followerRepo.getSubscribersOrSubscriptionsByFollowerId(id, subscribersOrSubscriptions);
    }
}
