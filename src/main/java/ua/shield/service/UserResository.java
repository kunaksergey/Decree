package ua.shield.service;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.User;

/**
 * Created by sa on 06.06.17.
 */
public interface UserResository extends CrudRepository<User,Integer> {
    User findByLogin(String login);
    User findByEmail(String login);
}
