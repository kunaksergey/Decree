package ua.shield.jsf.controller;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.shield.entity.User;
import ua.shield.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by sa on 12.06.17.
 */
@ManagedBean
@SessionScoped
public class UserJsfController implements Serializable {
    private final String PHOTO_DEFAULT_NAME="nonphoto.jpg";
    private FileUploadEvent fileUploadEvent;
    private User user;

    @ManagedProperty("#{jpaUserService}")
    private UserService jpaUserService;

    @ManagedProperty("#{messagesView}")
    private MessagesView messagesView;


    public UserJsfController() {
    }

    @PostConstruct
    public void init() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = jpaUserService.findByLogin(authentication.getName());
        }else{
            user = new User();
        }

    }

    public StreamedContent getPhotoStreamedContent() {
        if (fileUploadEvent != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(fileUploadEvent.getFile().getContents()));
        }
        if (user.getPhoto()!=null && user.getPhoto().length > 0) {
            return new DefaultStreamedContent(new ByteArrayInputStream(user.getPhoto()));
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PHOTO_DEFAULT_NAME).getFile());
        try {
            return new DefaultStreamedContent(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }


    public void fileUploadListener(FileUploadEvent fileUploadEvent) {
        this.fileUploadEvent = fileUploadEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getJpaUserService() {
        return jpaUserService;
    }

    public void setJpaUserService(UserService jpaUserService) {
        this.jpaUserService = jpaUserService;
    }

    public MessagesView getMessagesView() {
        return messagesView;
    }

    public void setMessagesView(MessagesView messagesView) {
        this.messagesView = messagesView;
    }

    public void save() {
        if (fileUploadEvent != null) {
            user.setPhoto(fileUploadEvent.getFile().getContents());
        }

        user = jpaUserService.save(user);

        if (user.getId() != 0) {
            messagesView.info(messagesView.getMessageFromResource("message","message.SaveIsSuccess"));
        } else {
            messagesView.info(messagesView.getMessageFromResource("message","message.SaveIsFail"));
        }

    }

    public Date getDate() {
        if (user.getBirthDate() == null) {
            return null;
        }
        Instant instant = user.getBirthDate().atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);

    }

    public void setDate(Date date) {
        user.setBirthDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
