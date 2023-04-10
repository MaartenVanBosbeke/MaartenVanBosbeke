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

import com.shopr.domains.articles.Fiction;
import com.shopr.domains.articles.NonFiction;
import com.shopr.services.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

//SHOW ALL BOOKS (F+NF)
    @GetMapping(value = "/books")
    public String getAllBooksFromDb(Model model) {
//        model.addAttribute("allBooks", bookService.getAllBooksFromDb());
        model.addAttribute("allFiction", bookService.getFictionFromDb());
        model.addAttribute("allNonFiction", bookService.getNonFictionFromDb());
        return "admin/books";
    }



    //FICTION

//ADD FICTION
    @GetMapping("/fictionAdd")
    public String showAddFictionPage(Model model) {
        model.addAttribute("newFiction", new Fiction());
        return "admin/fictionAdd";
    }
    @PostMapping("/fictionAdd")
    public String addFictionToDb(@ModelAttribute Fiction fiction){
        bookService.addFictionToDb(fiction);
        return "redirect:/books";
    }

//EDIT FICTION
    @GetMapping("/fictionEdit/{id}")
    public String showEditFictionPage(@PathVariable("id") long id, Model model){
        model.addAttribute("editFiction", bookService.findFictionById(id));
        return "admin/fictionEdit";
    }
    @PostMapping("/fictionEdit/{id}")
    public String editFictionById(@ModelAttribute Fiction fiction){
        bookService.editFictionById(fiction);
        return "redirect:/books";
    }

//REMOVE FICTION
    @GetMapping("/fictionRemove/{id}")
    public String showRemoveFictionPage(@PathVariable("id") long id, Model model){
        model.addAttribute("removeFiction", bookService.findFictionById(id));
        return "admin/fictionRemove";
    }
    @PostMapping("/fictionRemoved/{id}")
    public String removeFictionById(@ModelAttribute Fiction removeFiction){
        bookService.removeFictionFromDb(removeFiction);
        return "redirect:/books";
    }


//SEARCH FICTION
    @GetMapping("/fictionSearch")
    public String showSearchFictionPage(Model model) {
        model.addAttribute("fictionSearched", new Fiction());
        return "admin/fictionSearch";
    }
    @RequestMapping(value="/fictionSearched", method = {RequestMethod.POST, RequestMethod.GET})
    public String showFoundFiction(Model model, @ModelAttribute("fictionSearched")  Fiction fictionSearched) {
        List<Fiction> fictionList = bookService.searchFiction(fictionSearched);
        model.addAttribute("fictionFound", fictionList);
        return "admin/fictionFound";
    }



    //NONFICTION

//ADD NON-FICTION
    @GetMapping("/nonFictionAdd")
    public String showAddNonFictionPage(Model model) {
        model.addAttribute("newNonFiction", new NonFiction());
        return "admin/nonFictionAdd";
    }
    @PostMapping("/nonFictionAdd")
    public String addNonFictionToDb(@ModelAttribute NonFiction nonFiction){
        bookService.addNonFictionToDb(nonFiction);
        return "redirect:/books";
    }

//EDIT NON-FICTION
    @GetMapping("/nonFictionEdit/{id}")
    public String showEditNonFictionPage(@PathVariable("id") long id, Model model){
        model.addAttribute("editNonFiction", bookService.findNonFictionById(id));
        return "admin/nonFictionEdit";
    }
    @PostMapping("/nonFictionEdit/{id}")
    public String editNonFictionById(@ModelAttribute NonFiction nonFiction){
        bookService.editNonFictionById(nonFiction);
        return "redirect:/books";
    }

//REMOVE NON-FICTION
    @GetMapping("/nonFictionRemove/{id}")
    public String showRemoveNonFictionPage(@PathVariable("id") long id, Model model){
        model.addAttribute("removeNonFiction", bookService.findNonFictionById(id));
        return "admin/nonFictionRemove";
    }
    @PostMapping("/nonFictionRemoved/{id}")
    public String removeNonFictionById(@ModelAttribute NonFiction removeNonFiction){
        bookService.removeNonFictionFromDb(removeNonFiction);
        return "redirect:/books";
    }

//SEARCH NONFICTION
    @GetMapping("/nonFictionSearch")
    public String showSearchNonFictionPage(Model model) {
        model.addAttribute("nonFictionSearched", new NonFiction());
        return "admin/nonFictionSearch";
    }
    @RequestMapping(value="/nonFictionSearched", method = {RequestMethod.POST, RequestMethod.GET})
    public String showFoundNonFiction(Model model, @ModelAttribute("nonFictionSearched")  NonFiction nonFictionSearched) {
        List<NonFiction> nonFictionList = bookService.searchNonFiction(nonFictionSearched);
        model.addAttribute("nonFictionFound", nonFictionList);
        return "admin/nonFictionFound";
    }



}
