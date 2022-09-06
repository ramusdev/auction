package com.rb.auction.model.view;

public class MessageChatView {
    private int post;
    private int user;

    public MessageChatView(int post, int user) {
        this.post = post;
        this.user = user;
    }

    public MessageChatView() {
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageChatView{" +
                "post=" + post +
                ", user=" + user +
                '}';
    }
}
