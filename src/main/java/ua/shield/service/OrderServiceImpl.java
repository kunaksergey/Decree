package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Order;
import ua.shield.entity.User;

import java.util.*;

/**
 * Created by sa on 18.07.17.
 */
@Service("jpaOrderService")
@Repository
@Transactional
public class OrderServiceImpl
        implements OrderService {

    @Autowired
    private OrderResository orderResository;

    Map<Integer,Order> mapOrder=new HashMap<>();
    {
        mapOrder.put(1,new Order(1,0,"Подготовленный документ №1"));
        mapOrder.put(2,new Order(2,1,"В работе документ №2"));
        mapOrder.put(3,new Order(3,2,"Закончен документ №3"));
    }
    @Override
    public Order save(Order document) {
        return
                orderResository.save(document);
    }

    @Override
    public Order findById(int id) {
        return orderResository.findOne(id);
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderResository.findAll();
       // return new ArrayList<>(mapOrder.values());
    }

    @Override
    public List<Order> findAllByOwnerStatusAndSign(int statusId,int userId) {
        return orderResository.findAllByStatudAndX(statusId,userId);
    }

    @Override
    public List<Order> findAllByOwner(User owner) {
        return orderResository.findAllByOwner(owner);
    }


}
