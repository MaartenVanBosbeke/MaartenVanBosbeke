package com.shopr.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopr.services.ArticleService;
import com.shopr.services.BookService;
import com.shopr.services.GameService;
import com.shopr.services.LpService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private GameService gameService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LpService lpService;

//LIMITED ART
    @GetMapping("/articles")
    public String getAllArticlesFromDb(Model model){
        model.addAttribute("gamesTypeTitlePrice", gameService.getAllGamesFromDb());
        model.addAttribute("allFiction", bookService.getFictionFromDb());
        model.addAttribute("allNonFiction", bookService.getNonFictionFromDb());
        model.addAttribute("lpsTypeTitlePrice", lpService.getAllLpsFromDb());
        return "admin/articles";
    }

//EXPANDED ART
    @GetMapping("/articlesExpanded")
    public String getAllArticlesForExpanded(Model model){
        model.addAttribute("gamesTypeTitlePrice", gameService.getAllGamesFromDb());
        model.addAttribute("allFiction", bookService.getFictionFromDb());
        model.addAttribute("allNonFiction", bookService.getNonFictionFromDb());
        model.addAttribute("lpsTypeTitlePrice", lpService.getAllLpsFromDb());
        return "admin/articlesExpanded";
    }

}
