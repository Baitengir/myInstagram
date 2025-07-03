package myInstagram.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Follower;
import myInstagram.entities.User;
import myInstagram.entities.UserInfo;
import myInstagram.repositories.UserRepo;
import myInstagram.service.UserService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUserById(Long id, User user) {

    }

    @Override
    public User getUserByName(String username) {
        return userRepo.getUserByName(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepo.getUserByPhone(phone);
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
