package ua.shield.service;

import ua.shield.entity.Message;
import ua.shield.entity.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sa on 06.06.17.
 */
public interface MessageService {
    List<Message> findAll();
    List<Message> findAllOrderByDate();
    Message findById(int id);
    Message save(Message user);
    void delete(Message user);
}
