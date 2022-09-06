package com.rb.auction.service;

import com.rb.auction.model.MessageChat;

import java.util.List;

public interface InterfaceMessageChatService {
    MessageChat getById(int id);
    MessageChat add(MessageChat messageChat);
    MessageChat getByProductAndUser(int productId, List<Integer> users);
}
