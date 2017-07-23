package ua.shield.jsf.validator;

import ua.shield.entity.User;
import ua.shield.jsf.controller.MessagesView;
import ua.shield.jsf.controller.UserJsfController;
import ua.shield.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by sa on 20.06.17.
 */
@ManagedBean
@SessionScoped
public class LoginValidator implements Validator {
    @ManagedProperty("#{jpaUserService}")
    private UserService jpaUserService;

    @ManagedProperty("#{userJsfController}")
    private UserJsfController userJsfController;

    @ManagedProperty("#{messagesView}")
    private MessagesView messagesView;


    public UserService getJpaUserService() {
        return jpaUserService;
    }

    public void setJpaUserService(UserService jpaUserService) {
        this.jpaUserService = jpaUserService;
    }

    public UserJsfController getUserJsfController() {
        return userJsfController;
    }

    public void setUserJsfController(UserJsfController userJsfController) {
        this.userJsfController = userJsfController;
    }

    public MessagesView getMessagesView() {
        return messagesView;
    }

    public void setMessagesView(MessagesView messagesView) {
        this.messagesView = messagesView;
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        User user=userJsfController.getUser();
        User userByLogin=jpaUserService.findByLogin(value.toString());
        if(userByLogin!=null&&!user.equals(userByLogin)){
            FacesMessage msg = new FacesMessage(messagesView.getMessageFromResource("message","message.loginIsExists.validate"));
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }
}
