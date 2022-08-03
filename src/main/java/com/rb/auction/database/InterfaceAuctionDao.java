package com.rb.auction.database;

import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;

import java.util.List;
import java.util.Optional;

public interface InterfaceAuctionDao {
    int add(Auction auction);
    Optional<Auction> getById(int id);
    void update(Auction auction);
    int addBid(AuctionBet auctionBet);
    Optional<AuctionBet> getBidById(int id);
    List<Auction> getAll();
    List<Auction> getByName(String searchKey);
}
