package ua.shield.jsf.controller;

import org.primefaces.model.DualListModel;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.shield.entity.Order;
import ua.shield.entity.Sign;
import ua.shield.entity.User;
import ua.shield.service.OrderService;
import ua.shield.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by sa on 18.07.17.
 */
@ManagedBean
@SessionScoped
public class OrderCreateJsfController {

    @ManagedProperty("#{jpaOrderService}")
    OrderService jpaOrderService;

    @ManagedProperty("#{jpaUserService}")
    private UserService jpaUserService;

    private DualListModel<User> users;

    private Order order;

    @PostConstruct
     public void init() {
        //Users
        order = new Order();
        List<User> usersSource = new ArrayList<>(jpaUserService.findAll());
        List<User> usersTarget = new ArrayList<>();
        users = new DualListModel<>(usersSource, usersTarget);
    }

    public Order getOrder() {
        return order;
    }

    public OrderService getJpaOrderService() {
        return jpaOrderService;
    }

    public void setJpaOrderService(OrderService jpaOrderService) {
        this.jpaOrderService = jpaOrderService;
    }

    public UserService getJpaUserService() {
        return jpaUserService;
    }

    public void setJpaUserService(UserService jpaUserService) {
        this.jpaUserService = jpaUserService;
    }

    public DualListModel<User> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<User> users) {
        this.users = users;
    }



    public void save(){
        System.out.println("Записываем");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User owner = jpaUserService.findByLogin(authentication.getName());
            order.setOwner(owner);
            order.setOrderDateTimeCreated(LocalDateTime.now());
            List<User> target = users.getTarget();
            for (User user:target) {
                order.getSetSign().add(new Sign(order,user));
            }
            System.out.println(order);
            jpaOrderService.save(order);
            order=new Order();

        }else{
          //Тут код если ползователь не залогинился
        }


    }
}
