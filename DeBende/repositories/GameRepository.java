package com.shopr.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopr.domains.articles.Game;

@Repository
public class GameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Game> getAllGamesFromDb(){
        try {
            return entityManager.createNamedQuery("findAllGames", Game.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Game findGameById(Long id) {
        try {
            return entityManager.createNamedQuery("findGameById", Game.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void editGameById(Game editGame) {
        entityManager.merge(editGame);
    }

    @Transactional
    public void addNewGameToDb(Game newGame) {
        entityManager.persist(newGame);
    }

    @Transactional
    public void deleteGameFromDb(Long id) {
        Game game = findGameById(id);
        entityManager.remove(game);
    }

    public List<Game> searchGame(Game searchedGame){
        try {
            List<Game> gamesFound = entityManager.createNamedQuery("findGameBySearch", Game.class)
                    .setParameter("id", searchedGame.getId())
                    .setParameter("title", searchedGame.getTitle())
                    .setParameter("publ", searchedGame.getPublisher())
                    .setParameter("genre", searchedGame.getGameGenre())
                    .setParameter("price", searchedGame.getPrice())
                    .setParameter("age", searchedGame.getMinAge())
                    .setParameter("supp", searchedGame.getSupplierId())
                    .getResultList();
            return gamesFound;
        } catch (Exception e) {
            return null;
        }
    }


}


//SEARCH
//        String title, publ, genre;
//        int age;
//        long id, supp;
//        double price;
//        if (searchedGame.getTitle() != null) {
//            title = searchedGame.getTitle();
//        } else {
//            title = null;
//        }
//        if (searchedGame.getPublisher() != null) {
//            publ = searchedGame.getPublisher();
//        } else {
//            publ = null;
//        }
//        if (searchedGame.getGameGenre() != null) {
//            genre = searchedGame.getGameGenre().toString();
//        } else {
//            genre = null;
//        }
//        if (searchedGame.getMinAge() > 0) {
//            age = searchedGame.getMinAge();
//        } else {
//            age = -1;
//        }
//        if (searchedGame.getId() != null) {
//            id = searchedGame.getId();
//        } else {
//            id = Long.parseLong(null);
//        }
//        if (searchedGame.getSupplierId() != null) {
//            supp = searchedGame.getSupplierId();
//        } else {
//            supp = Long.parseLong(null);
//        }
//        if (searchedGame.getPrice() != null) {
//            price = searchedGame.getPrice();
//        } else {
//            price = Double.parseDouble(null);
//        }

//        String title = searchedGame.getTitle();
//        String publ = searchedGame.getPublisher();
//        GameGenre genre = searchedGame.getGameGenre();
//        int age = searchedGame.getMinAge();
//        long id = searchedGame.getId();
//        long supp = searchedGame.getSupplierId();
//        double price = searchedGame.getPrice();



//            List<Game> gamesFound = entityManager.createNamedQuery("findGameBySearch", Game.class)
//                    .setParameter("id", id)
//                    .setParameter("title", "%" + title + "%")
//                    .setParameter("publ", "%" + publ + "%")
//                    .setParameter("genre", genre)
//                    .setParameter("price", price)
//                    .setParameter("age", age)
//                    .setParameter("supp", supp)
//                    .getResultList();