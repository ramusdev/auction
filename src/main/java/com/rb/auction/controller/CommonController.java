package com.rb.auction.controller;

import com.rb.auction.database.InterfaceUserDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.Product;
import com.rb.auction.service.InterfaceAuctionService;
import com.rb.auction.service.InterfaceProductService;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
public class CommonController {
    @Autowired
    InterfaceUserDao interfaceUserDAO;
    // @Autowired
    // InterfaceAuctionService interfaceAuctionService;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    InterfaceProductService interfaceProductService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainRedirect() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainShow(Model model) {
        List<Product> products = this.interfaceProductService.getAllProducts();
        model.addAttribute("mproducts", products);

        SessionObject sessionObject = null;
        if (this.sessionObject.isLogged()) {
            sessionObject = this.sessionObject;
        }
        model.addAttribute("msession", sessionObject);

        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String mainSearch(@RequestParam String searchKey, Model model) {
        SessionObject sessionObject = null;
        if (this.sessionObject.isLogged()) {
            sessionObject = this.sessionObject;
        }
        model.addAttribute("msession", sessionObject);

        List<Product> products = this.interfaceProductService.getByName(searchKey);
        model.addAttribute("mproducts", products);

        return "main";
    }

}
