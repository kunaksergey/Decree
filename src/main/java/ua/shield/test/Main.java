package ua.shield.test;

import org.springframework.context.support.GenericXmlApplicationContext;
import ua.shield.entity.Message;
import ua.shield.entity.Order;
import ua.shield.entity.Sign;
import ua.shield.entity.User;
import ua.shield.service.MessageService;
import ua.shield.service.OrderService;
import ua.shield.service.UserService;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 06.06.17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        GenericXmlApplicationContext context=new GenericXmlApplicationContext();
        context.load("classpath:app-context.xml");
        context.refresh();
        System.runFinalization();
        OrderService orderService = context.getBean("jpaOrderService", OrderService.class);
        UserService userService = context.getBean("jpaUserService", UserService.class);
        Order order=new Order();
        order.setCaption("test");
        order.setDescription("test");
        order.setOwner(userService.findById(14));
        order.getSetSing().add(new Sign(order,userService.findById(2)));
        order.getSetSing().add(new Sign(order,userService.findById(3)));
        System.out.println(order);
        orderService.save(order);

    }
//
//    public static void print(User user){
//        System.out.println(user);
//        user.getRoles().forEach(System.out::println);
//        user.getMessageList().forEach(System.out::println);
//    }

}
