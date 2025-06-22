package myInstagram.service.serviceImpl;

import myInstagram.entities.User;
import myInstagram.repositories.repositoriesImpl.UserRepoImpl;
import myInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepoImpl userRepoImpl;

    public UserServiceImpl(UserRepoImpl userRepoImpl) {
        this.userRepoImpl = userRepoImpl;
    }

    @Override
    public void save(User user) {
        userRepoImpl.save(user);
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
