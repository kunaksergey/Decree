package ua.shield.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sa on 06.06.17.
 */
@Service("jpaMessageService")
@Repository
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageResository messageResository;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Message> findAll() {
        return Lists.newLinkedList(messageResository.findAll());
    }

    @Override
    public List<Message> findAllOrderByDate() {
        List<Message>messages= em.createNamedQuery("Message.findAllOrderByDate",Message.class).getResultList();
        return messages;
    }

    @Transactional(readOnly = true)
    public Message findById(int id) {
        return messageResository.findOne(id);
    }

    public Message save(Message message) {
        return messageResository.save(message);
    }

    public void delete(Message message) {
        messageResository.delete(message);
    }
}
