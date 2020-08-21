package com.poly.controller;


import com.poly.model.User;
import com.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping(value = {"/login"})
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord
                      ,ModelMap model) {
        try {
            User user = userService.findByUserName(userName);
            if (user != null) {
                if (user.getPassWord().equals(passWord)) {

                    return "redirect:/index";
                } else {
                    model.put("errorLogin", "\n" +
                            "Wrong password");
                }
            } else {
                model.put("errorLogin", "\n" +
                        "Wrong account");
            }

        } catch (Exception e) {
            model.put("errorLogin", "\n" +
                    "Account does not exist");
        }
        return "/login";
    }
    
    @GetMapping("/register")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView saveUser( @ModelAttribute("user") User user, BindingResult bindingResult) {


        if (!bindingResult.hasFieldErrors()) {
            userService.save(user);
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/register");
        return modelAndView;
    }
}


