package ua.shield.service;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.User;
import ua.shield.jsf.exeption.UserIsNotRegisteredExeption;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.Set;

/**
 * Created by sa on 06.06.17.
 */
@Service("jpaUserService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserResository userResository;

    @Transactional(readOnly = true)
    public Set<User> findAll() {
        return Sets.newHashSet(userResository.findAll());
    }

    @Transactional(readOnly = true)
    public User findById(int id) {
        return userResository.findOne(id);
    }

    @Override
    public User findByLogin(String login) {
        return userResository.findByLogin(login);
    }

    @Override
    public User findByEmail(String login) {
        return userResository.findByEmail(login);
    }

    public User save(User user) {
        return userResository.save(user);
    }

    public void delete(User user) {
        userResository.delete(user);
    }

    @Override
    public User registeredUser() throws UserIsNotRegisteredExeption {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userResository.findByLogin(authentication.getName());
        } else {
            throw new UserIsNotRegisteredExeption("User is not registered");
        }
    }
}
