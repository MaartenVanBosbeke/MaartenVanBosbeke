package com.shopr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopr.domains.articles.Book;
import com.shopr.domains.articles.Fiction;
import com.shopr.domains.articles.NonFiction;
import com.shopr.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooksFromDb(){
        return bookRepository.getAllBooksFromDb();
    }



    //FICTION
    public List<Fiction> getFictionFromDb(){
        return bookRepository.getFictionFromDb();
    }

    public Fiction findFictionById(long id) {
        return bookRepository.findFictionById(id);
    }

    public void addFictionToDb(Fiction fiction) {
        bookRepository.addFictionToDb(fiction);
    }

    public void editFictionById(Fiction fiction) {
        bookRepository.editFictionById(fiction);
    }

    public void removeFictionFromDb(Fiction removeFiction) {
        bookRepository.removeFictionFromDb(removeFiction);
    }

    public List<Fiction> searchFiction(Fiction fictionSearched) {
        return bookRepository.searchFiction(fictionSearched);
    }



    //NONFICTION

    public List<NonFiction> getNonFictionFromDb(){
        return bookRepository.getNonFictionFromDb();
    }

    public NonFiction findNonFictionById(long id) {
        return bookRepository.findNonFictionById(id);
    }

    public void addNonFictionToDb(NonFiction nonFiction) {
        bookRepository.addNonFictionToDb(nonFiction);
    }

    public void editNonFictionById(NonFiction nonFiction) {
        bookRepository.editNonFictionById(nonFiction);
    }

    public void removeNonFictionFromDb(NonFiction removeNonFiction) {
        bookRepository.removeNonFictionFromDb(removeNonFiction);
    }

    public List<NonFiction> searchNonFiction(NonFiction nonFictionSearched) {
        return bookRepository.searchNonFiction(nonFictionSearched);
    }

}
