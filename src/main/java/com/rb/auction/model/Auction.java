package com.rb.auction.model;

import com.rb.auction.sorter.SortByDate;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(mappedBy = "auction")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @SortComparator(SortByDate.class)
    private SortedSet<AuctionBet> auctionBets = new TreeSet<>();

    public Auction(int id, LocalDateTime startDate, LocalDateTime endDate, Status status, Product product, SortedSet<AuctionBet> auctionBets) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.product = product;
        this.auctionBets = auctionBets;
    }

    public Auction() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SortedSet<AuctionBet> getAuctionBets() {
        return auctionBets;
    }

    public void setAuctionBets(SortedSet<AuctionBet> auctionBets) {
        this.auctionBets = auctionBets;
    }

    public void setAuctionBet(AuctionBet auctionBet) {
        this.auctionBets.add(auctionBet);
    }

    public enum Status {
        OPEN,
        CLOSE
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", product=" + product +
                ", auctionBets=" + auctionBets +
                '}';
    }
}
