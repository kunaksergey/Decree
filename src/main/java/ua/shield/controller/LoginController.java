package ua.shield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sa on 09.06.17.
 */
@Controller
public class LoginController {
    @RequestMapping("/logout")
    public String logOut(){
        return "redirect:/login?logout";
    }

    @RequestMapping("/log")
    public String logIn(){
        return "test";
    }
}
