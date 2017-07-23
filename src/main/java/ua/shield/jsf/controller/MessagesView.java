package ua.shield.jsf.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by sa on 20.06.17.
 */
@ManagedBean
@SessionScoped
public class MessagesView {

    public void info(String type) {
        info(type,null);
    }

    public void warn(String type) {
        warn(type,null);
    }

    public void error(String type) {
        error(type,null);
    }

    public void fatal(String type) {
        fatal(type,null);
    }

    public void info(String type,String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, type, msg));
    }

    public void warn(String type,String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, type, msg));
    }

    public void error(String type,String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, type, msg));
    }

    public void fatal(String type,String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, type, msg));
    }


    public String getMessageFromResource(String baseName,String label){
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context .getViewRoot().getLocale();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, loader);
        String msg = bundle.getString(label);
        return msg;
    }
}