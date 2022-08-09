package com.rb.auction.sorter;

import com.rb.auction.model.AuctionBet;

import java.util.Comparator;

public class SortByDate implements Comparator<AuctionBet> {
    @Override
    public int compare(AuctionBet o1, AuctionBet o2) {
        if (o1.getPrice() > o2.getPrice()) {
            return -1;
        } else if (o1.getPrice() < o2.getPrice()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

