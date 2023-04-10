package com.shopr.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopr.services.GameService;
import com.shopr.services.OrderArticleService;
import com.shopr.services.UserService;

@Controller
public class UserGameController {

    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private OrderArticleService orderArticleService;



//SHOW GAMES FOR USER
    @GetMapping("/userGames")
    public String showAllGames(Model model) {
        model.addAttribute("allGames", gameService.getAllGamesFromDb());
        return "user/userGame";
    }




}
