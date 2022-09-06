package com.rb.auction.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rb.auction.sorter.SortByDate;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity(name = "chats")
public class MessageChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private MessageParticipants messageParticipants;
    // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    // private User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "chat")
    @SortComparator(SortByDate.class)
    private SortedSet<MessageItem> messageItems = new TreeSet<MessageItem>();

    // @JsonCreator
    public MessageChat(int id, LocalDateTime date, MessageParticipants messageParticipants, Product product, SortedSet<MessageItem> messageItems) {
        this.id = id;
        this.date = date;
        this.messageParticipants = messageParticipants;
        this.product = product;
        this.messageItems = messageItems;
    }

    public MessageChat() {
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

    public MessageParticipants getMessageParticipants() {
        return messageParticipants;
    }

    public void setMessageParticipants(MessageParticipants messageParticipants) {
        this.messageParticipants = messageParticipants;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SortedSet<MessageItem> getMessageItems() {
        return messageItems;
    }

    public void setMessageItems(SortedSet<MessageItem> messageItems) {
        this.messageItems = messageItems;
    }

    @Override
    public String toString() {
        return "MessageChat{" +
                "id=" + id +
                ", date=" + date +
                ", messageParticipants=" + messageParticipants +
                ", product=" + product +
                ", messageItems=" + messageItems +
                '}';
    }
}
