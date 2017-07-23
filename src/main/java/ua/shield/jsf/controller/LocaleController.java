package ua.shield.jsf.controller;

import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by sa on 19.06.17.
 */
@ManagedBean
@SessionScope
public class LocaleController implements Serializable{
   private static Map<String,Locale> locales;

    static {
        locales = new LinkedHashMap<>();
        locales.put("en", new Locale("en", "UK"));
        locales.put("ru", new Locale("ru", "RU"));
    }


    public Locale getLocale() {
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("local");
        String nameLocaleFromCookie = cookie.getValue().toString();
        return (Locale) locales.get(nameLocaleFromCookie);
    }

}
