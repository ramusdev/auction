package com.rb.auction.service;

import com.rb.auction.model.Auction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CloseAuctionService {
    @Autowired
    InterfaceAuctionService auctionService;

    // @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void updateStatusAuction() {
        log.info("Scheduled: ");
        List<Auction> auctions = this.auctionService.getAllOpen();
        LocalDateTime currentDate = LocalDateTime.now();

        List<Auction> a = auctions.stream()
                .filter(e -> {
                    return e.getEndDate().isBefore(currentDate);
                })
                .map(e -> {
                    e.setStatus(Auction.Status.CLOSE);
                    return e;
                })
                .map(e -> {
                    this.auctionService.update(e);
                    return e;
                })
                .collect(Collectors.toList());

    }
}
