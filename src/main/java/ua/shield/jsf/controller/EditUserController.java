package ua.shield.jsf.controller;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.stereotype.Service;
import ua.shield.entity.User;
import ua.shield.jsf.model.UserManager;
import ua.shield.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 17.06.17.
 */
@ManagedBean
@ViewScoped
public class EditUserController implements Serializable{

   @ManagedProperty("#{jpaUserService}")
    UserService jpaUserService;

    @ManagedProperty("#{messagesView}")
    private MessagesView messagesView;

    private Set<User> userSet;

    @PostConstruct
    public void init(){
        userSet=jpaUserService.findAll();
    }

    public UserService getJpaUserService() {
        return jpaUserService;
    }

    public void setJpaUserService(UserService jpaUserService) {
        this.jpaUserService = jpaUserService;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public MessagesView getMessagesView() {
        return messagesView;
    }

    public void setMessagesView(MessagesView messagesView) {
        this.messagesView = messagesView;
    }

    public void onRowEdit(RowEditEvent event) {
        User user=(User) event.getObject();
        jpaUserService.save(user);
        messagesView.info(messagesView.getMessageFromResource("message","message.userEdited"));
    }

    public void onRowCancel(RowEditEvent event) {
        messagesView.info(messagesView.getMessageFromResource("message","message.editCanceled"));
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
