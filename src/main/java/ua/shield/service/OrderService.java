package ua.shield.service;

import ua.shield.entity.Order;
import ua.shield.entity.User;

import java.util.List;

/**
 * Created by sa on 18.07.17.
 */
public interface OrderService {
    Order save(Order document);
    Order findById(int id);
    List<Order> findAll();

}
