package com.rb.auction.controller;

import com.rb.auction.model.AuctionBet;
import com.rb.auction.service.InterfaceAuctionService;
import com.rb.auction.service.InterfaceProductService;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
public class AuctionController {
    @Autowired
    InterfaceProductService interfaceProductService;
    @Autowired
    InterfaceAuctionService interfaceAuctionService;
    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value = "/auction/addbet/{id}", method = RequestMethod.POST)
    public String addBet(@ModelAttribute AuctionBet auctionBet, @PathVariable int id, @RequestParam int postid) {
        this.interfaceAuctionService.addBetToAuction(id, auctionBet);
        return "redirect:/product/" + postid;
    }
}
