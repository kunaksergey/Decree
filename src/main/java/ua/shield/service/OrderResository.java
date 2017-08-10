package ua.shield.service;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.Message;
import ua.shield.entity.Order;

/**
 * Created by sa on 06.06.17.
 */
public interface OrderResository extends CrudRepository<Order,Integer> {

 }
