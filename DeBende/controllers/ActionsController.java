package com.shopr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActionsController {

//SHOW ERROR PAGE
    @GetMapping("/error")
    public String showErrorPage(){
        return "error";
    }

}
