package com.rb.auction.controller;

import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;
import com.rb.auction.model.Product;
import com.rb.auction.model.view.AuctionView;
import com.rb.auction.service.AuctionService;
import com.rb.auction.service.InterfaceAuctionService;
import com.rb.auction.service.ProductService;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    AuctionService auctionService;
    @Autowired
    InterfaceAuctionService interfaceAuctionService;
    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value = "/addproduct", method = RequestMethod.GET)
    public String productAddShow(Model model) {
        model.addAttribute("mproduct", new Product());
        model.addAttribute("mauction", new AuctionView());
        model.addAttribute("msession", this.sessionObject);

        if (this.sessionObject.isLogged()) {
            return "addproduct";
        }

        return "redirect:/main";
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    public String productAdd(@ModelAttribute Product product, @ModelAttribute AuctionView auctionView) {
        int auctionId = auctionService.addAuction(auctionView);
        productService.addProduct(product, auctionId);

        return "redirect:/main";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String productShow(@PathVariable int id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("rproduct", product);
        model.addAttribute("rauctionbid", new AuctionBet());

        return "product";
    }
}
