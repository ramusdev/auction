package com.rb.auction.database;

import com.rb.auction.model.MessageChat;

import java.util.List;
import java.util.Optional;

public interface InterfaceMessageChatDao {
    Optional<MessageChat> getById(int id);
    MessageChat add(MessageChat messageChat);
    Optional<MessageChat> getByProductAndUser(int productId, List<Integer> users);
}
