package com.rb.auction.model.view;

import com.rb.auction.model.MessageChat;
import com.rb.auction.model.User;
import com.rb.auction.model.MessageItem;

import java.time.LocalDateTime;

public class MessageItemView extends MessageItem {
    private int productId;
    private int userId;
    private String text;

    public MessageItemView(int id, LocalDateTime date, String text, MessageChat chat, User user, int productId, int userId, String text1) {
        super(id, date, text, chat, user);
        this.productId = productId;
        this.userId = userId;
        this.text = text1;
    }

    public MessageItemView(int productId, int userId, String text) {
        this.productId = productId;
        this.userId = userId;
        this.text = text;
    }

    public MessageItemView() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
