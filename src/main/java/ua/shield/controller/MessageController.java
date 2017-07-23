package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Message;
import ua.shield.service.MessageService;
import ua.shield.service.UserService;

import java.time.LocalDateTime;

/**
 * Created by sa on 07.06.17.
 */
@Controller
public class MessageController {
      @Autowired
      private MessageService jpaMessageService;
      @Autowired
      private UserService jpaUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllUserAndMessage(Model model) {
        model.addAttribute("messages", jpaMessageService.findAllOrderByDate());
        model.addAttribute("messageModel", new Message());
        return "listMessage";
    }

    @RequestMapping(value = "/message/add", method = RequestMethod.POST)
    public String addMessage(@ModelAttribute("messageModel") Message message) {
        User userSecurity = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userSecurity != null) {
            ua.shield.entity.User user = jpaUserService.findByLogin(userSecurity.getUsername());
            message.setUser(user);
            message.setMessageDateTime(LocalDateTime.now());
            jpaMessageService.save(message);
        }
        return "redirect:/";
    }
}
