package com.shopr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopr.domains.articles.Article;
import com.shopr.repositories.ArticleRepository;
import com.shopr.repositories.BookRepository;
import com.shopr.repositories.GameRepository;
import com.shopr.repositories.LpRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LpRepository lpRepository;

    public List<Article> getAllArticlesFromDb(){
        return articleRepository.getAllArticlesFromDb();
    }



}
