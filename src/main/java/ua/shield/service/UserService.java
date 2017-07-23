package ua.shield.service;

import ua.shield.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by sa on 06.06.17.
 */
public interface UserService {
    Set<User> findAll();
    User findById(int id);
    User findByLogin(String login);
    User findByEmail(String login);
    User save(User user);
    void delete(User user);
}
