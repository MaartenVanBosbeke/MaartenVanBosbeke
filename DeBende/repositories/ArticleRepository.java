package com.shopr.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.shopr.domains.articles.Article;
import com.shopr.domains.articles.Book;
import com.shopr.domains.articles.Game;
import com.shopr.domains.articles.Lp;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Article> getAllArticlesFromDb() {
        List<Article> allArticlesList = new ArrayList<>();

        List<Book> booksList;
        try {
            booksList = entityManager.createNamedQuery("allBooks", Book.class).getResultList();
        } catch (Exception e) {
            return null;
        }

        List<Game> gamesList;
        try {
            gamesList = entityManager.createNamedQuery("findAllGames", Game.class).getResultList();
        } catch (Exception e) {
            return null;
        }

        List<Lp> lpsList;
        try {
            lpsList = entityManager.createNamedQuery("findAllLp", Lp.class).getResultList();
        } catch (Exception e) {
            return null;
        }

        if(booksList.isEmpty() == false){
            allArticlesList.addAll(booksList);
        }
        if(gamesList.isEmpty() == false) {
            allArticlesList.addAll(gamesList);
        }
        if(lpsList.isEmpty() == false) {
            allArticlesList.addAll(lpsList);
        }
        return allArticlesList;
    }



}
