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

import com.shopr.domains.articles.Lp;
import com.shopr.services.LpService;

@Controller
public class LpController {

    @Autowired
    private LpService lpService;

//SHOW LP
    @GetMapping("/lps")
    public String getAllLpsFromDb(Model model){
        model.addAttribute("allLps", lpService.getAllLpsFromDb());
        return "admin/lps";
    }


//ADD LP
    @GetMapping("/lpAdd")
    public String showLpAddPage(Model model){
        model.addAttribute("newLp", new Lp());
        return "admin/lpAdd";
    }
    @PostMapping("/lpAdd")
    public String addLpToDb(@ModelAttribute Lp newLp){
        lpService.addLpToDb(newLp);
        return "redirect:/lps";
    }


//EDIT LP
    @GetMapping("/lpEdit/{id}")
    public String showEditLpPage(@PathVariable("id") long id, Model model){
        model.addAttribute("editLp", lpService.findLpById(id));
        return "admin/lpEdit";
    }
    @PostMapping("/lpEdit/{id}")
    public String editLpInDb(@ModelAttribute Lp lp){
        lpService.editLpInDb(lp);
        return "redirect:/lps";
    }


//REMOVE LP
    @GetMapping("/lpRemove/{id}")
    public String showRemoveLpPage(@PathVariable("id") long id, Model model){
        model.addAttribute("removeLp", lpService.findLpById(id));
        return "admin/lpRemove";
    }
    @PostMapping("/lpRemoved/{id}")
    public String removelpFromDb(@PathVariable("id") long id){
        lpService.removeLpById(id);
        return "redirect:/lps";
    }


//SEARCH
    @GetMapping("/lpSearch")
    public String showSearchLpPage(Model model) {
        model.addAttribute("lpSearched", new Lp());
        return "admin/lpSearch";
    }
    @RequestMapping(value="/lpSearched", method = {RequestMethod.POST, RequestMethod.GET})
    public String showFoundLp(Model model, @ModelAttribute("gameSearched")  Lp lpSearched) {
        List<Lp> lpList = lpService.searchLp(lpSearched);
        model.addAttribute("lpFound", lpList);
        return "admin/lpFound";
    }
}
