package com.rb.auction.service;

import com.rb.auction.database.InterfaceAuctionDao;
import com.rb.auction.database.InterfaceProductDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;
import com.rb.auction.model.Product;
import com.rb.auction.model.view.AuctionView;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class AuctionService implements InterfaceAuctionService {
    @Autowired
    InterfaceAuctionService auctionService;
    @Autowired
    InterfaceAuctionDao auctionDao;
    @Autowired
    SessionObject sessionObject;
    @Autowired
    InterfaceBetService betService;

    /*
    @Override
    public void addAuction(AuctionView auctionView, int productId) {
        Optional<Product> productOptional = this.interfaceProductDao.getProductById(productId);

        if (productOptional.isEmpty()) {
            return;
        }

        Product product = productOptional.get();
        Auction auction = auctionView.parentCopy();

        LocalDateTime startDay = LocalDateTime.now();
        LocalDateTime endDay = startDay.plusDays(auctionView.getDuration());

        auction.setStartDate(startDay);
        auction.setEndDate(endDay);
        // auction.setProduct(product);
        // auction.setUser(sessionObject.getUser());
        auction.setStatus(Auction.Status.OPEN);

        this.interfaceAuctionDao.add(auction);
    }
    */

    public List<Auction> getAll() {
        return this.auctionDao.getAll();
    }

    @Override
    public List<Auction> getByName(String searchKey) {
        List<Auction> auctions = this.auctionDao.getByName(searchKey);
        return auctions;
    }

    @Override
    public Auction addAuction(AuctionView auctionView) {
        // Optional<Product> productOptional = this.interfaceProductDao.getProductById(productId);
        // if (productOptional.isEmpty()) {
            // return;
        // }
        // Product product = productOptional.get();

        Auction auction = auctionView.parentCopy();

        LocalDateTime startDay = LocalDateTime.now();
        LocalDateTime endDay = startDay.plusDays(auctionView.getDuration());

        auction.setStartDate(startDay);
        auction.setEndDate(endDay);
        auction.setStatus(Auction.Status.OPEN);

        int auctionId = this.auctionDao.add(auction);
        auction.setId(auctionId);

        return auction;
    }

    @Override
    public void update(Auction auction) {
        this.auctionDao.update(auction);
    }

    @Override
    public Auction getById(int id) {
        Optional<Auction> auctionOptional = auctionDao.getById(id);

        if (auctionOptional.isEmpty()) {
            return null;
        }

        return auctionOptional.get();
    }

    @Override
    public void addBetToAuction(int auctionId, AuctionBet auctionBet) {
        Auction auction = this.getById(auctionId);
        Set<AuctionBet> bets = auction.getAuctionBets();




        // if (auction.getStatus().equals(Auction.Status.CLOSE)) {
            // return;
        // }

        /*
        Set<AuctionBet> auctionBets = auction.getAuctionBets();
        double maxBetPrice = auction.getProduct().getPrice();

        for (AuctionBet auctionBetItem : auctionBets) {
            if (auctionBetItem.getPrice() > maxBetPrice) {
                maxBetPrice = auctionBetItem.getPrice();
            }
        }

        if (maxBetPrice > auctionBet.getPrice()) {
            return;
        }
        */



        auctionBet.setUser(this.sessionObject.getUser());
        auctionBet.setDate(LocalDateTime.now());
        auctionBet.setId(0);
        auctionBet.setAuction(auction);

        auction.getAuctionBets().add(auctionBet);
        this.auctionService.update(auction);



        // this.betService.add(auctionBet);

    }

    @Override
    public void updateStatus(int id) {
        Optional<Auction> auctionOptions = auctionDao.getById(id);

        if (auctionOptions.isEmpty()) {
            return;
        }

        Auction auction = auctionOptions.get();
        if (auction.getStatus().equals(Auction.Status.CLOSE)) {
            return;
        }

        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isAfter(auction.getEndDate())) {
            auction.setStatus(Auction.Status.CLOSE);
            this.auctionDao.update(auction);
        }
    }


}
