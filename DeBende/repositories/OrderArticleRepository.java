package com.shopr.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopr.domains.OrderArticle;

@Repository
public class OrderArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderArticle> showAllGamesInCart() {
        return entityManager.createNamedQuery("allOrderArticles", OrderArticle.class).getResultList();
    }

    public OrderArticle findOAById(long id) {
        return entityManager.createNamedQuery("findOAById", OrderArticle.class).setParameter("id", id).getSingleResult();
    }

    @Transactional
    public void addToCart(OrderArticle gameOrder) {
        Double priceTotal = gameOrder.getAmount() * gameOrder.getPriceSingle();
        gameOrder.setPriceTotal(priceTotal);
        entityManager.merge(gameOrder);
    }

    @Transactional
    public void editOAInDb(OrderArticle editedOA) {
        Double priceTotal = editedOA.getAmount() * editedOA.getPriceSingle();
        editedOA.setPriceTotal(priceTotal);
        entityManager.merge(editedOA);
    }

    @Transactional
    public void removeOAById(long id) {
        OrderArticle oaToDelete = findOAById(id);
        entityManager.remove(oaToDelete);
    }

    @Transactional
    public void clearCart() {
        entityManager.createQuery("DELETE FROM OrderArticle").executeUpdate();
    }
}
