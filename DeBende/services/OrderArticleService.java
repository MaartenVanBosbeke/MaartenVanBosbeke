package com.shopr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopr.domains.OrderArticle;
import com.shopr.repositories.OrderArticleRepository;

@Service
public class OrderArticleService {

    @Autowired
    private OrderArticleRepository orderArticleRepository;

    public List<OrderArticle> showAllGamesInCart() {
        return orderArticleRepository.showAllGamesInCart();
    }

    public void addToCart(OrderArticle gameOrder) {
        orderArticleRepository.addToCart(gameOrder);
    }

    public OrderArticle findOAById(long id) {
        return orderArticleRepository.findOAById(id);
    }

    public void editOAInDb(OrderArticle editedOA) {
        orderArticleRepository.editOAInDb(editedOA);
    }

    public void removeOAById(long id) {
        orderArticleRepository.removeOAById(id);
    }

    public void clearCart() {
        orderArticleRepository.clearCart();
    }
}
