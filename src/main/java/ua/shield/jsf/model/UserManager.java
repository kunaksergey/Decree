package ua.shield.jsf.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.entity.User;
import ua.shield.service.UserService;

import javax.inject.Named;

/**
 * Created by sa on 12.06.17.
 */
@Service
public class UserManager {
    @Autowired
    private UserService jpaUserService;

    public void add(UserModel userModel){
        jpaUserService.save(UserModel.UserFromUserModel(userModel));
    }

    public User findByLogin(String login){
        return jpaUserService.findByLogin(login);
    };
}
