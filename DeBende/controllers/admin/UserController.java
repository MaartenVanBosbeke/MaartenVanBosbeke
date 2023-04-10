package com.shopr.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shopr.domains.User;
import com.shopr.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//SHOW USERS
    @GetMapping("/users")
    public String showUsersPage(Model model){
        model.addAttribute("usersList", userService.showListOfUsers());
        return "admin/users";
    }


//CREATE NEW USER + SHOW "created account" PAGE AFTERWARDS
    @GetMapping("/createAccount")
    public String showCreateAccount(Model model) {
        model.addAttribute("newUser", new User());
        return "account/accountCreate";
    }
    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute User user) {
        userService.createAccount(user);
        return "redirect:/accountCreated";
    }
    @GetMapping("/accountCreated")
    public String confirmNewAccount() {
        return "account/accountCreated";
    }


//EDIT USER
    @GetMapping("/userEdit/{id}")
    public String showEditUserPage(@PathVariable("id") long id, Model model){
        model.addAttribute("editUser", userService.findUserById(id));
        return "admin/userEdit";
    }
    @PostMapping("/userEdit/{id}")
    public String editUserInDb(@ModelAttribute User user){
        userService.editUserInDb(user);
        return "redirect:/users";
    }


//REMOVE USER
    @GetMapping("/userRemove/{id}")
    public String showRemoveUserPage(@PathVariable("id") long id, Model model){
        model.addAttribute("removeUser", userService.findUserById(id));
        return "admin/userRemove";
    }
    @PostMapping("/userRemoved/{id}")
    public String removeUserFromDb(@PathVariable("id") long id){
        userService.removeUserFromDb(id);
        return "redirect:/users";
    }


//SEARCH USER
    @GetMapping("/userSearch")
    public String showSearchUserPage(Model model) {
        model.addAttribute("userSearched", new User());
        return "admin/userSearch";
    }
    @RequestMapping(value="/userSearched", method = {RequestMethod.POST, RequestMethod.GET})
    public String showFoundUser(Model model, @ModelAttribute("userSearched") User userSearched) {
        List<User> usersList = userService.searchUser(userSearched);
        model.addAttribute("usersFound", usersList);
        return "admin/userFound";
    }
}
