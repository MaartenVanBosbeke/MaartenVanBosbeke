package com.shopr.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopr.services.BookService;
import com.shopr.services.GameService;
import com.shopr.services.LpService;

@Controller
public class UserArticleController {

    @Autowired
    private GameService gameService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LpService lpService;

//LTD VIEW
    @GetMapping("/userArticles")
    public String showUserArticlesLtd(Model model){
        model.addAttribute("gamesTypeTitlePrice", gameService.getAllGamesFromDb());
        model.addAttribute("allFiction", bookService.getFictionFromDb());
        model.addAttribute("allNonFiction", bookService.getNonFictionFromDb());
        model.addAttribute("lpsTypeTitlePrice", lpService.getAllLpsFromDb());
        return "user/userArticles";
    }



//EXPANDED VIEW
    @GetMapping("/userArticlesExpanded")
    public String showUserArticlesExpanded(Model model){
        model.addAttribute("gamesTypeTitlePrice", gameService.getAllGamesFromDb());
        model.addAttribute("allFiction", bookService.getFictionFromDb());
        model.addAttribute("allNonFiction", bookService.getNonFictionFromDb());
        model.addAttribute("lpsTypeTitlePrice", lpService.getAllLpsFromDb());
        return "user/userArticlesExpanded";
    }
}
