package com.rb.auction.controller;

import com.rb.auction.model.*;
import com.rb.auction.model.view.AuctionView;
import com.rb.auction.model.view.TagView;
import com.rb.auction.service.*;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class ProductController {
    @Autowired
    AuctionService auctionService;
    @Autowired
    InterfaceAuctionService interfaceAuctionService;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    InterfaceTagService tagService;
    @Autowired
    InterfaceProductService productService;
    @Autowired
    InterfaceUserService userService;

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String productAddShow(Model model) {
        SessionObject sessionObject = null;
        if (this.sessionObject.isLogged()) {
            sessionObject = this.sessionObject;
        }

        model.addAttribute("msession", sessionObject);
        model.addAttribute("mproduct", new Product());
        model.addAttribute("mauction", new AuctionView());

        List<Tag> tags = this.tagService.getAll();
        model.addAttribute("mtag", tags);

        if (this.sessionObject.isLogged()) {
            return "addproduct";
        }

        return "no-login";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String productAdd(@ModelAttribute Product product, @ModelAttribute AuctionView auctionView, @ModelAttribute TagView tagView) {
        // @RequestParam(value="tagField[]") String[] tagFields
        this.productService.addProductAuctionTags(product, auctionView, tagView);

        return "redirect:/main";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String productShow(@PathVariable int id, Model model) {
        if (! this.sessionObject.isLogged()) {
            return "no-login";
        }

        User currentUser = null;
        Product product = this.productService.getById(id);
        Auction auction = product.getAuction();
        Set<Tag> tags = product.getSortedTags();
        User user = product.getUser();
        Set<AuctionBet> auctionBets = auction.getAuctionBets();

        SessionObject sessionObject = null;
        if (this.sessionObject.isLogged()) {
            sessionObject = this.sessionObject;
            currentUser = sessionObject.getUser();
        }

        model.addAttribute("msessions", sessionObject);
        model.addAttribute("mproduct", product);
        model.addAttribute("mauction", auction);
        model.addAttribute("mtag", tags);
        model.addAttribute("muser", user);
        model.addAttribute("mcurrentuser", currentUser);
        model.addAttribute("mauctionfield", new AuctionBet());
        model.addAttribute("mauctionbet", auctionBets);

        User finalCurrentUser = currentUser;
        List<User> users = auction.getAuctionBets().stream()
                .filter(e -> {
                    if (e.getUser().getId() != finalCurrentUser.getId()) {
                        return true;
                    }
                    return false;
                })
                .map(e -> {
                    return e.getUser();
                })
                .collect(Collectors.toList());
        model.addAttribute("mchatuser", users);

        return "product";
    }
}
