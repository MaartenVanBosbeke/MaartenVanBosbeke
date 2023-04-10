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

import com.shopr.domains.articles.Game;
import com.shopr.services.GameService;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

//SHOW GAMES
    @GetMapping("/games")
    public String showAllGames(Model model){
        model.addAttribute("allGames", gameService.getAllGamesFromDb());
        return "admin/game";
    }

//ADD
    @GetMapping("/gameAdd")
    public String showGameAdd(Model model){
        model.addAttribute("newGame", new Game());
        return "admin/gameAdd";
    }
    @PostMapping("/gameAdd")
    public String addNewGameToDb(@ModelAttribute Game newGame){
        gameService.addNewGameToDb(newGame);
        return "redirect:/games";
    }

//EDIT
    @GetMapping("/gameEdit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("editGame", gameService.findGameById(id));
        return "admin/gameEdit";
    }
    @PostMapping("/gameEdit/{id}")
    public String editGame(@ModelAttribute Game editedGame) {
        gameService.editGameById(editedGame);
        return "redirect:/games";
    }

//REMOVE
    @GetMapping("/gameRemove/{id}")
    public String removeGameFromDb(@PathVariable("id") long id, Model model){
        model.addAttribute("removeGame", gameService.findGameById(id));
        return "admin/gameRemove";
    }
    @PostMapping("/gameRemoved/{id}")
    public String removeGameFromDb(@PathVariable("id") long id){
        gameService.deleteGameFromDb(id);
        return "redirect:/games";
    }

//SEARCH
    @GetMapping("/gameSearch")
    public String showSearchGamePage(Model model) {
        model.addAttribute("gameSearched", new Game());
        return "admin/gameSearch";
    }
    @RequestMapping(value="/gameSearched", method = {RequestMethod.POST, RequestMethod.GET})
    public String showFoundGame(Model model, @ModelAttribute("gameSearched")  Game gameSearched) {
        List<Game> gamesList = gameService.searchGame(gameSearched);
        model.addAttribute("gameFound", gamesList);
        return "admin/gameFound";
    }

}

//    @PostMapping("/gameSearch")
//    public String searchForGames(@ModelAttribute("gameSearched") Game gameSearched){
//        gameService.searchGame(gameSearched);
//        return "admin/gameFound";
//    }
//    @GetMapping("/gameSearch/gameFound")
//    public String showFoundGames(Model model, Game gameSearched){
//        model.addAttribute("gamesFound", gameService.searchGame(gameSearched));
//        return "admin/gameFound";
//    }
//    @GetMapping("/gameSearch")
//    public String showSearchGamePage(@Param("gameSearched") Model model) {
//        model.addAttribute("gameSearched", new Game());
//        return "admin/gameFound";
//    }