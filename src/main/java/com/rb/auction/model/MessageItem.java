package com.rb.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "messages")
public class MessageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private String text;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private MessageChat chat;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public MessageItem(int id, LocalDateTime date, String text, MessageChat chat, User user) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.chat = chat;
        this.user = user;
    }

    public MessageItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageChat getChat() {
        return chat;
    }

    public void setChat(MessageChat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "MessageItem{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", chat=" + chat +
                ", user=" + user +
                '}';
    }
}