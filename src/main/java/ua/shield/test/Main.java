package ua.shield.test;

import org.springframework.context.support.GenericXmlApplicationContext;
import ua.shield.entity.Message;
import ua.shield.entity.User;
import ua.shield.service.MessageService;
import ua.shield.service.UserService;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sa on 06.06.17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        GenericXmlApplicationContext context=new GenericXmlApplicationContext();
        context.load("classpath:app-context.xml");
        context.refresh();
        System.runFinalization();
        MessageService messageService = context.getBean("jpaMessageService", MessageService.class);
        UserService userService = context.getBean("jpaUserService", UserService.class);
        messageService.findAllOrderByDate().forEach(m-> System.out.println(m.getUser().getLogin()));

    }

    public static void print(User user){
        System.out.println(user);
        user.getRoles().forEach(System.out::println);
        user.getMessageList().forEach(System.out::println);
    }

}
