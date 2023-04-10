package com.shopr.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopr.domains.articles.Lp;

@Repository
public class LpRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Lp> getAllLpsFromDb(){
        try {
            return entityManager.createNamedQuery("findAllLp", Lp.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Lp findLpById(long id) {
        try {
            return entityManager.createNamedQuery("findLpById", Lp.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void addLpToDb(Lp lp) {
        entityManager.persist(lp);
    }

    @Transactional
    public void editLpInDb(Lp lp) {
        entityManager.merge(lp);
    }

    @Transactional
    public void removeLpById(long id) {
        Lp lp = findLpById(id);
        entityManager.remove(lp);
    }

    public List<Lp> searchLp(Lp lpSearched) {
        try {
            List<Lp> lpsFound = entityManager.createNamedQuery("findLpBySearch", Lp.class)
                    .setParameter("id", lpSearched.getId())
                    .setParameter("artist", lpSearched.getArtist())
                    .setParameter("title", lpSearched.getTitle())
                    .setParameter("price", lpSearched.getPrice())
                    .setParameter("genre", lpSearched.getLpGenre())
                    .setParameter("supp", lpSearched.getSupplierId())
                    .getResultList();
            return lpsFound;
        } catch (Exception e) {
            return null;
        }
    }
}
