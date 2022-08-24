package com.rb.auction.service;

import com.rb.auction.database.InterfaceMessageItemDao;
import com.rb.auction.model.MessageItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageItemService implements InterfaceMessageItemService {
    @Autowired
    InterfaceMessageItemDao messageDao;

    @Override
    public void add(MessageItem messageItem) {
        this.messageDao.add(messageItem);
    }
}
