package ua.shield.jsf.controller;

import org.primefaces.model.DualListModel;
import ua.shield.entity.Order;
import ua.shield.entity.User;
import ua.shield.service.OrderService;
import ua.shield.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    {
        order = new Order();
    }

    @PostConstruct
     public void init() {
        //Users
        List<User> usersSource = new ArrayList<>(jpaUserService.findAll());
        List<User> usersTarget = new ArrayList<User>();
        users = new DualListModel<User>(usersSource, usersTarget);
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

    public void changeOrder(String orderId){
        int id=Integer.parseInt(orderId);
        this.order=jpaOrderService.findById(id);
    }
}
