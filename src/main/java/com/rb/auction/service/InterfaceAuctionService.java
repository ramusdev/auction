package com.rb.auction.service;

import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;
import com.rb.auction.model.view.AuctionView;

import java.util.List;

public interface InterfaceAuctionService {
    Auction getById(int id);
    void addBetToAuction(int auctionId, AuctionBet auctionBet);
    void updateStatus(int id);
    List<Auction> getAll();
    List<Auction> getAllOpen();
    List<Auction> getByName(String searchKey);
    Auction addAuction(AuctionView auction);
    void update(Auction auction);
}
