package myInstagram.service;

import myInstagram.entities.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void updateUserById(Long id, User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUserById(Long id);
}
