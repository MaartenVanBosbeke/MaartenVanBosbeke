package com.shopr.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopr.domains.articles.Book;
import com.shopr.domains.articles.Fiction;
import com.shopr.domains.articles.NonFiction;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> getAllBooksFromDb(){
        try {
            return entityManager.createNamedQuery("allBooks", Book.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }



//FICTION
    public List<Fiction> getFictionFromDb(){
        try {
            return entityManager.createNamedQuery("allFiction", Fiction.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void addFictionToDb(Fiction fiction) {
        entityManager.persist(fiction);
    }

    public Fiction findFictionById(long id) {
        try {
            return entityManager.createNamedQuery("fictionById", Fiction.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void editFictionById(Fiction fiction) {
        entityManager.merge(fiction);
    }

    @Transactional
    public void removeFictionFromDb(Fiction removeFiction) {
        Fiction fiction = findFictionById(removeFiction.getId());
        entityManager.remove(fiction);
    }

    public List<Fiction> searchFiction(Fiction fictionSearched) {
        List<Fiction> fictionFound = entityManager.createNamedQuery("findFictionBySearch", Fiction.class)
                .setParameter("id", fictionSearched.getId())
                .setParameter("title", fictionSearched.getTitle())
                .setParameter("author", fictionSearched.getAuthor())
                .setParameter("contents", fictionSearched.getContents())
                .setParameter("price", fictionSearched.getPrice())
                .setParameter("pages", fictionSearched.getPages())
                .setParameter("supp", fictionSearched.getSupplierId())
                .setParameter("fgenre", fictionSearched.getFictionGenre())
                .getResultList();
        return fictionFound;
    }



//NONFICTION
    public List<NonFiction> getNonFictionFromDb(){
        try {
            return entityManager.createNamedQuery("allNonFiction", NonFiction.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public NonFiction findNonFictionById(long id) {
        try {
            return entityManager.createNamedQuery("nonFictionById", NonFiction.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void addNonFictionToDb(NonFiction nonFiction) {
        entityManager.persist(nonFiction);
    }

    @Transactional
    public void editNonFictionById(NonFiction nonFiction) {
        entityManager.merge(nonFiction);
    }

    @Transactional
    public void removeNonFictionFromDb(NonFiction removeNonFiction) {
        NonFiction removedNonFiction = findNonFictionById(removeNonFiction.getId());
        entityManager.remove(removedNonFiction);
    }

    public List<NonFiction> searchNonFiction(NonFiction nonFictionSearched) {
        List<NonFiction> nonFictionFound = entityManager.createNamedQuery("findNonFictionBySearch", NonFiction.class)
                .setParameter("id", nonFictionSearched.getId())
                .setParameter("title", nonFictionSearched.getTitle())
                .setParameter("author", nonFictionSearched.getAuthor())
                .setParameter("price", nonFictionSearched.getPrice())
                .setParameter("pages", nonFictionSearched.getPages())
                .setParameter("supp", nonFictionSearched.getSupplierId())
                .setParameter("nfsubject", nonFictionSearched.getNonFictionSubject())
                .getResultList();
        return nonFictionFound;
    }
}
