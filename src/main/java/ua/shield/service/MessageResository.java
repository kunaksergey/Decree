package ua.shield.service;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.Message;
import ua.shield.entity.User;

import java.util.List;

/**
 * Created by sa on 06.06.17.
 */
public interface MessageResository extends CrudRepository<Message,Integer> {

 }
