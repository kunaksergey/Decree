package ua.shield.jsf.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.shield.entity.Order;
import ua.shield.entity.User;
import ua.shield.service.OrderService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sa on 17.07.17.
 */
@ManagedBean
@SessionScoped
public class OrderJsfController {

    @ManagedProperty("#{jpaOrderService}")
    OrderService jpaOrderService;

    Order currentOrder;

    private String statusPrepared;

    public OrderService getJpaOrderService() {
        return jpaOrderService;
    }

    public void setJpaOrderService(OrderService jpaOrderService) {
        this.jpaOrderService = jpaOrderService;
    }

    @PostConstruct
    public void init() {
        currentOrder=new Order();
    }

    public List<Order> getOrderListWithStatusPrepared(){
        addMessage("Welcome to Primefaces!!");
        return jpaOrderService.findAll().stream().filter(d->d.getStatus()== Order.STATUS_PREPARED).collect(Collectors.toList());
     }

    public List<Order> getOrderListWithStatusInWork(){
        return jpaOrderService.findAll().stream().filter(d->d.getStatus()== Order.STATUS_IN_WORK).collect(Collectors.toList());
    }

    public List<Order> getOrderListWithStatusCompleted(){
        return jpaOrderService.findAll().stream().filter(d->d.getStatus()== Order.STATUS_COMPLETED).collect(Collectors.toList());
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
