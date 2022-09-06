package com.rb.auction.controller.rest;

import com.rb.auction.exceptions.EntityDoNotExistsException;
import com.rb.auction.model.*;
import com.rb.auction.model.view.MessageChatView;
import com.rb.auction.model.view.MessageItemView;
import com.rb.auction.service.InterfaceMessageChatService;
import com.rb.auction.service.InterfaceMessageItemService;
import com.rb.auction.service.InterfaceProductService;
import com.rb.auction.service.InterfaceUserService;
import com.rb.auction.session.SessionObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value = "/rest/message/send", method = RequestMethod.POST)
    public void messageSend(@RequestBody MessageItemView messageItemView) {
        User user = null;
        if (this.sessionObject.isLogged()) {
            user = this.sessionObject.getUser();
        }

        int productId = messageItemView.getProductId();
        int userOneId = messageItemView.getUserId();
        int userCurrentId = user.getId();
        String messageText = messageItemView.getText();

        MessageChat messageChat = null;
        List<Integer> users = Arrays.asList(userOneId, userCurrentId);
        messageChat = this.messageChatService.getByProductAndUser(productId, users);
        User userCurrent = this.userService.getUserById(userCurrentId);

        if (messageChat == null) {
            User userOne = this.userService.getUserById(userOneId);
            Product product = this.productService.getById(productId);

            MessageParticipants messageParticipants = new MessageParticipants();
            messageParticipants.getUsers().add(userOne);
            messageParticipants.getUsers().add(userCurrent);

            messageChat = new MessageChat();
            messageChat.setDate(LocalDateTime.now());
            messageChat.setMessageParticipants(messageParticipants);
            messageChat.setProduct(product);

            this.messageChatService.add(messageChat);
        }

        MessageItem messageItem = new MessageItem();
        messageItem.setUser(userCurrent);
        messageItem.setText(messageText);
        messageItem.setChat(messageChat);
        messageItem.setDate(LocalDateTime.now());

        this.messageItemService.add(messageItem);
    }

    @RequestMapping(value = "/rest/message/chattwo", method = RequestMethod.POST)
    public Set<MessageItem> getChatByUserAndProduct(@RequestBody MessageChatView messageChatView) {
        User user = null;
        if (this.sessionObject.isLogged()) {
            user = this.sessionObject.getUser();
        }

        int productId = messageChatView.getPost();
        int userIdOne = user.getId();
        int userIdTwo = messageChatView.getUser();

        List<Integer> users = Arrays.asList(userIdOne, userIdTwo);
        MessageChat messageChat = this.messageChatService.getByProductAndUser(productId, users);

        if (messageChat != null) {
            return messageChat.getMessageItems();
        } else {
            throw new EntityDoNotExistsException();
        }
    }
}
