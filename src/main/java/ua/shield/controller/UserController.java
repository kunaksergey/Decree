package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.shield.service.UserService;

/**
 * Created by sa on 07.06.17.
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService jpaUserService;

    @RequestMapping(value = "/userList",method =RequestMethod.GET)
    public String  showAllUser(Model model){
        model.addAttribute("users",jpaUserService.findAll());
        return "userList";
    }
}
