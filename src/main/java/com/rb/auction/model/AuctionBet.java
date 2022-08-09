package com.rb.auction.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "bets")
public class AuctionBet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private Auction auction;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public AuctionBet(int id, LocalDateTime date, double price, Auction auction, User user) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.auction = auction;
        this.user = user;
    }

    public AuctionBet() {

    }

    @Override
    public String toString() {
        return "AuctionBet{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", auction=" + auction +
                ", user=" + user +
                '}';
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
