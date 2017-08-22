package ua.shield.jsf.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.shield.entity.User;
import ua.shield.jsf.exeption.UserIsNotRegisteredExeption;
import ua.shield.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by sa on 20.08.17.
 */
@ManagedBean
@SessionScoped
public class SecurityController {


    @ManagedProperty("#{jpaUserService}")
    private UserService jpaUserService;

    public User getRegisteredUser() throws UserIsNotRegisteredExeption {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return jpaUserService.findByLogin(authentication.getName());
        } else {
        throw new UserIsNotRegisteredExeption("User is not registered");
        }
    }


    public UserService getJpaUserService() {
        return jpaUserService;
    }

    public void setJpaUserService(UserService jpaUserService) {
        this.jpaUserService = jpaUserService;
    }
}