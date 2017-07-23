package ua.shield.jsf.validator;

import ua.shield.entity.User;
import ua.shield.jsf.controller.MessagesView;
import ua.shield.jsf.controller.UserJsfController;
import ua.shield.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sa on 13.06.17.
 */
@ManagedBean
@RequestScoped
public class EmailValidator implements Validator {
    @ManagedProperty("#{jpaUserService}")
    private UserService jpaUserService;

    @ManagedProperty("#{userJsfController}")
    private UserJsfController userJsfController;

    @ManagedProperty("#{messagesView}")
    private MessagesView messagesView;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\." +
                    "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
                    "(\\.[A-Za-z]{2,})$";
    private Pattern pattern;

    public EmailValidator() {
        this.pattern = Pattern.compile(EMAIL_PATTERN);
    }

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
        Matcher matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("E-mail validation failed.",
                    "Invalid E-mail format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        User user=userJsfController.getUser();
        User userByEmail=jpaUserService.findByEmail(value.toString());
        if(userByEmail!=null&&!user.equals(userByEmail)){
            FacesMessage msg = new FacesMessage(messagesView.getMessageFromResource("message","message.emailIsExists.validate"));
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }
}
