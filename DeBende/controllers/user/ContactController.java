package com.shopr.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopr.domains.Contact;
import com.shopr.services.ContactService;

@Controller
public class ContactController {

    @Autowired
    public ContactService contactService;

//CONTACTFORM
    @GetMapping(value = "/contact")
    public String showContactPage(Model model){
        model.addAttribute("message", new Contact());
        return "user/userContact";
    }
    @PostMapping(value = "/contact/send")
    public String sendEmail(@RequestParam("attachment") MultipartFile file, @ModelAttribute Contact contact){
        if(file.isEmpty()){
            contactService.sendMessageWithoutAttachment(contact);
        } else {
            contactService.sendMessageWithAttachment(contact, file);
        }
        return "redirect:/userArticles";
    }

}
