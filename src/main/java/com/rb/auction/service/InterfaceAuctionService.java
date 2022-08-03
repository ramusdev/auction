package com.rb.auction.service;

import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;
import com.rb.auction.model.Product;
import com.rb.auction.model.view.AuctionView;

import java.util.List;

public interface InterfaceAuctionService {
    Auction getAuctionById(int id);
    void addBetToAuction(int auctionId, AuctionBet auctionBet);
    void updateStatus(int id);
    List<Auction> getAll();
    List<Auction> getByName(String searchKey);
    int addAuction(AuctionView auction);
}
