package com.shopr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopr.domains.User;
import com.shopr.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

//LOGIN CHECK + redirect
    @GetMapping("/")
    public String logIn(Model model) {
        model.addAttribute("findUser", new User());
        return "index";
    }
    @PostMapping("/loggedIn")
    public String showLoggedIn(@ModelAttribute("findUser") User user) {
        User userFound = userService.findUserByNameAndPassword(user);
        if (userFound != null) {
            if (userFound.getSecurityLevel() == 1) {
                return "redirect:/adminLoggedIn";
            } else {
                return "redirect:/userLoggedIn";
            }
        } else {
            return "redirect:/accountNotFound";
        }
    }


//LOGIN REDIRECT (after confirm/deny login)
    @GetMapping("/adminLoggedIn")
    public String confirmAdminLogin() {
        return "account/adminLoggedIn";
    }
    @GetMapping("/userLoggedIn")
    public String comfirmUserLogin() {
        return "account/userLoggedIn";
    }
    @GetMapping("/accountNotFound")
    public String denyLogin() {
        return "account/accountNotFound";
    }


//CREATE USER => USERCONTROLLER

}
