package com.rb.auction.database.hibernate;

import com.rb.auction.database.InterfaceAuctionDao;
import com.rb.auction.model.Auction;
import com.rb.auction.model.AuctionBet;

import java.util.List;
import java.util.Optional;

public class AuctionDaoStub implements InterfaceAuctionDao {
    @Override
    public int add(Auction auction) {
        return 1;
    }

    @Override
    public Optional<Auction> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Auction auction) {

    }

    @Override
    public int addBid(AuctionBet auctionBet) {
        return 0;
    }

    @Override
    public Optional<AuctionBet> getBidById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Auction> getAll() {
        return null;
    }

    @Override
    public List<Auction> getByName(String searchKey) {
        return null;
    }
}
