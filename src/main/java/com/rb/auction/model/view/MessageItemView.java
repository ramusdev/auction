package com.rb.auction.model.view;

import com.rb.auction.model.MessageChat;
import com.rb.auction.model.User;
import com.rb.auction.model.MessageItem;

import java.time.LocalDateTime;

public class MessageItemView extends MessageItem {
    private int product;
    private String text;

    public MessageItemView(int id, LocalDateTime date, String text, MessageChat chat, User user, int product, String text1) {
        super(id, date, text, chat, user);
        this.product = product;
        this.text = text1;
    }

    public MessageItemView(int product, String text) {
        this.product = product;
        this.text = text;
    }

    public MessageItemView() {

    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
