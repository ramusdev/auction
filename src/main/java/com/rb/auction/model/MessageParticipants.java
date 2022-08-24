package com.rb.auction.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "participants")
public class MessageParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "participant_user",
            joinColumns = {@JoinColumn(name = "participant_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private Set<User> users = new HashSet();

    // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinColumn(name = "chat_id", referencedColumnName = "id")
    // private MessageChat messageChat;

    public MessageParticipants(int id, Set<User> users) {
        this.id = id;
        this.users = users;
    }

    public MessageParticipants() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
