package com.rb.auction.controller.rest;

import com.rb.auction.model.*;
import com.rb.auction.model.view.MessageItemView;
import com.rb.auction.service.InterfaceMessageChatService;
import com.rb.auction.service.InterfaceMessageItemService;
import com.rb.auction.service.InterfaceProductService;
import com.rb.auction.service.InterfaceUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Set;

@Log4j2
@RestController
public class MessageController {
    @Autowired
    InterfaceUserService userService;
    @Autowired
    InterfaceMessageChatService messageChatService;
    @Autowired
    InterfaceMessageItemService messageItemService;
    @Autowired
    InterfaceProductService productService;

    @RequestMapping(value = "/rest/message/send", method = RequestMethod.POST)
    public void messageSend(@RequestBody MessageItemView messageItemView) {
        int productId = messageItemView.getProduct();
        int userId = 2;
        String messageText = messageItemView.getText();

        MessageChat messageChat = null;

        Product product = this.productService.getById(productId);
        User userProduct = product.getUser();

        User userCurrent = this.userService.getUserById(userId);
        messageChat = this.messageChatService.getByProductAndUser(productId, userCurrent.getId());

        if (messageChat == null) {
            MessageParticipants messageParticipants = new MessageParticipants();
            messageParticipants.getUsers().add(userProduct);
            messageParticipants.getUsers().add(userCurrent);

            messageChat = new MessageChat();
            messageChat.setDate(LocalDateTime.now());
            messageChat.setMessageParticipants(messageParticipants);
            messageChat.setProduct(product);
            messageChat.setUser(userCurrent);

            this.messageChatService.add(messageChat);
        }

        MessageItem messageItem = new MessageItem();
        messageItem.setUser(userCurrent);
        messageItem.setText(messageText);
        messageItem.setChat(messageChat);
        messageItem.setDate(LocalDateTime.now());

        this.messageItemService.add(messageItem);
    }

    @RequestMapping(value = "/rest/message/get", method = RequestMethod.GET)
    public Set<MessageItem> messageGet() {
        int productId = 100;
        int userId = 2;

        MessageChat messageChat = this.messageChatService.getByProductAndUser(productId, userId);
        Set<MessageItem> messageItems = messageChat.getMessageItems();

        // log.info("Message chat: {}", messageChat.getMessageItems());
        return messageItems;
    }
}
