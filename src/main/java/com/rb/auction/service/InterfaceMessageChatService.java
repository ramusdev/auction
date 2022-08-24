package com.rb.auction.service;

import com.rb.auction.model.MessageChat;

public interface InterfaceMessageChatService {
    MessageChat getById(int id);
    MessageChat add(MessageChat messageChat);
    MessageChat getByProductAndUser(int productId, int userId);
}
