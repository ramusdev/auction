package com.rb.auction.service;

import com.rb.auction.database.InterfaceBetDao;
import com.rb.auction.model.AuctionBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService implements InterfaceBetService {
    @Autowired
    InterfaceBetDao betDao;

    @Override
    public void add(AuctionBet bet) {
        this.betDao.add(bet);
    }
}
