package ua.shield.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.shield.entity.Message;
import ua.shield.entity.Order;
import ua.shield.entity.User;

import java.util.List;

/**
 * Created by sa on 06.06.17.
 */
public interface OrderResository extends CrudRepository<Order,Integer> {

 @Query("select o from Order o join o.owner ow join o.setSign s join s.user u where o.status=:statusId and(ow.id=:userId or u.id=:userId) group by o.id")
 List<Order> findAllByStatudAndX(@Param("statusId") int statusId, @Param("userId") int userId);
 List<Order> findAllByOwner(User owner);
 }
