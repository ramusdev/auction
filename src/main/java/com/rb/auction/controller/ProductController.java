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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
    public String productAddShow(Model model) {
        model.addAttribute("mproduct", new Product());
        model.addAttribute("mauction", new AuctionView());
        model.addAttribute("msession", this.sessionObject);

        List<Tag> tags = this.tagService.getAll();
        model.addAttribute("mtag", tags);

        // log.info("Tags: {}", tags);
        // if (this.sessionObject.isLogged()) {
        return "addproduct";
        // }

        // return "redirect:/main";
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    public String productAdd(@ModelAttribute Product product, @ModelAttribute AuctionView auctionView, @ModelAttribute TagView tagView) {
        // int auctionId = auctionService.addAuction(auctionView);
        // productService.addProduct(product, auctionId);

        // @RequestParam(value="tagField[]") String[] tagFields
        // log.info("Tag: {}", Arrays.toString(tagFields));

        // log.info("Tag: {}", tagView);
        this.productService.addProductAuctionTags(product, auctionView, tagView);

        return "redirect:/main";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String productShow(@PathVariable int id, Model model) {

        Product product = this.productService.getById(id);
        Auction auction = product.getAuction();
        Set<Tag> tags = product.getTags();
        User user = product.getUser();
        Set<AuctionBet> auctionBets = auction.getAuctionBets();

        SessionObject sessionObject = null;
        if (this.sessionObject.isLogged()) {
            sessionObject = this.sessionObject;
        }

        model.addAttribute("msessions", sessionObject);
        model.addAttribute("mproduct", product);
        model.addAttribute("mauction", auction);
        model.addAttribute("mtag", tags);
        model.addAttribute("muser", user);
        model.addAttribute("mauctionfield", new AuctionBet());
        model.addAttribute("mauctionbet", auctionBets);

        // model.addAttribute("mauction", auction);
        // this.interfaceAuctionService.updateStatus(id);
        // Set<AuctionBet> auctionBets = auction.getAuctionBets();
        // model.addAttribute("rauctionbets", auctionBets);


        return "product";
    }
}
