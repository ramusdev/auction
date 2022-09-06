package com.rb.auction.service;

import com.rb.auction.database.hibernate.MessageChatDao;
import com.rb.auction.model.MessageChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageChatService implements InterfaceMessageChatService {
    @Autowired
    MessageChatDao messageChatDao;

    @Override
    public MessageChat getById(int id) {
        Optional<MessageChat> messageChatOptional = this.messageChatDao.getById(id);

        if (messageChatOptional.isEmpty()) {
            return null;
        } else {
            return messageChatOptional.get();
        }
    }

    @Override
    public MessageChat add(MessageChat messageChat) {
        return this.messageChatDao.add(messageChat);
    }

    @Override
    public MessageChat getByProductAndUser(int productId, List<Integer> users) {
        Optional<MessageChat> messageChatOptional = this.messageChatDao.getByProductAndUser(productId, users);

        if (messageChatOptional.isEmpty()) {
            return null;
        } else {
            return messageChatOptional.get();
        }
    }
}
