package com.rb.auction.service;

import com.rb.auction.database.InterfaceAuctionDao;
import com.rb.auction.database.InterfaceProductDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;
import com.rb.auction.model.Product;
import com.rb.auction.model.view.AuctionView;
import com.rb.auction.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuctionService implements InterfaceAuctionService {
    @Autowired
    InterfaceProductDao interfaceProductDao;
    @Autowired
    InterfaceAuctionDao interfaceAuctionDao;
    @Autowired
    SessionObject sessionObject;

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
        return this.interfaceAuctionDao.getAll();
    }

    @Override
    public List<Auction> getByName(String searchKey) {
        List<Auction> auctions = this.interfaceAuctionDao.getByName(searchKey);
        return auctions;
    }

    @Override
    public int addAuction(AuctionView auctionView) {
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

        return this.interfaceAuctionDao.add(auction);
    }

    @Override
    public Auction getAuctionById(int id) {
        Optional<Auction> auctionOptional = interfaceAuctionDao.getById(id);

        if (auctionOptional.isEmpty()) {
            return null;
        }

        return auctionOptional.get();
    }

    @Override
    public void addBetToAuction(int auctionId, AuctionBet auctionBet) {
        Optional<Auction> auctionOptional = interfaceAuctionDao.getById(auctionId);

        if (auctionOptional.isEmpty()) {
            return;
        }

        Auction auction = auctionOptional.get();
        if (auction.getStatus().equals(Auction.Status.CLOSE)) {
            return;
        }

        // double maxBetPrice = auction.getProduct().getPrice();
        double maxBetPrice = 10;
        Set<AuctionBet> auctionBets = auction.getAuctionBets();

        for (AuctionBet auctionBetItem : auctionBets) {
            if (auctionBetItem.getPrice() > maxBetPrice) {
                maxBetPrice = auctionBetItem.getPrice();
            }
        }

        if (maxBetPrice > auctionBet.getPrice()) {
            return;
        }

        auctionBet.setUser(this.sessionObject.getUser());
        auctionBet.setDate(LocalDateTime.now());
        auctionBet.setId(0);
        auction.setAuctionBet(auctionBet);

        interfaceAuctionDao.update(auction);
    }

    @Override
    public void updateStatus(int id) {
        Optional<Auction> auctionOptions = interfaceAuctionDao.getById(id);

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
            this.interfaceAuctionDao.update(auction);
        }
    }


}
