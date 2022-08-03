package com.rb.auction.model.view;

import com.rb.auction.model.Auction;

public class AuctionView extends Auction {
    private int productId;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Auction parentCopy() {
        Auction auction = new Auction();
        auction.setAuctionBets(this.getAuctionBets());
        auction.setStatus(this.getStatus());
        // auction.setProduct(this.getProduct());
        auction.setStartDate(this.getStartDate());
        auction.setEndDate(this.getEndDate());
        auction.setId(this.getId());
        // auction.setUser(this.getUser());

        return auction;
    }

    @Override
    public String toString() {
        return "AuctionView{" +
                "productId=" + productId +
                ", duration=" + duration +
                '}';
    }
}
