package com.shopr.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopr.domains.OrderArticle;
import com.shopr.services.GameService;
import com.shopr.services.OrderArticleService;

@Controller
public class UserCartController {

    @Autowired
    private GameService gameService;
    @Autowired
    private OrderArticleService orderArticleService;


//SHOW SHOPPING CART WITH CURRENT ITEMS
    @GetMapping("/cart")
    public String showCartPage(Model model) {
        model.addAttribute("cart", orderArticleService.showAllGamesInCart());
        return "user/userCart";
    }

//SHOW ORDER A GAME PAGE
    @GetMapping("/userGameOrder/{id}")
    public String orderGameById(@PathVariable("id") long id, Model model){
        model.addAttribute("game", gameService.findGameById(id));
        model.addAttribute("orderGame", new OrderArticle());
        return "user/userGameOrder";
    }
    @PostMapping("/confirmUserGameOrder/{id}")
    public String confirmOrderGame(@ModelAttribute OrderArticle gameOrder){
        orderArticleService.addToCart(gameOrder);
        return "redirect:/cart";
    }



//EDIT ORDERARTICLE
    @GetMapping("/userEditOA/{id}")
    public String showEditOAPage(@PathVariable("id") long id, Model model){
        model.addAttribute("editOrder", orderArticleService.findOAById(id));
        return "user/userOAEdit";
    }
    @PostMapping("/userEditOA/{id}")
    public String editOAInDb(@ModelAttribute("editOrder") OrderArticle editedOA){
        orderArticleService.editOAInDb(editedOA);
        return "redirect:/cart";
    }



//REMOVE OA FROM CART
    @GetMapping("/userDeleteOA/{id}")
    public String removeGameFromDb(@PathVariable("id") long id, Model model){
        model.addAttribute("removeOA", orderArticleService.findOAById(id));
        return "user/userOARemove";
    }
    @PostMapping("/userDeletedOA/{id}")
    public String removeGameFromDb(@PathVariable("id") long id){
        orderArticleService.removeOAById(id);
        return "redirect:/cart";
    }



//CLEAR ENTIRE CART
    @GetMapping("/cartClear")
    public String clearCartpage() {
        orderArticleService.clearCart();
        return "redirect:/cart";
    }

}
