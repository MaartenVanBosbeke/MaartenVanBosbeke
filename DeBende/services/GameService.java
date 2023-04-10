package com.shopr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopr.domains.articles.Game;
import com.shopr.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGamesFromDb(){
        return gameRepository.getAllGamesFromDb();
    }

    public Game findGameById(Long id) {
        return gameRepository.findGameById(id);
    }

    public void editGameById(Game editGame){ gameRepository.editGameById(editGame); }

    public void addNewGameToDb(Game newgame){
        gameRepository.addNewGameToDb(newgame);
    }

    public void deleteGameFromDb(Long id) {
        gameRepository.deleteGameFromDb(id);
    }

    public List<Game> searchGame(Game searchedGame) {
        return gameRepository.searchGame(searchedGame);
    }


}
