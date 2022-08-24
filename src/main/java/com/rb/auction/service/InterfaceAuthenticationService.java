package com.rb.auction.service;

import com.rb.auction.model.view.RegisterUser;

public interface InterfaceAuthenticationService {
    void register(RegisterUser registerUser);
    void login(String login, String password);
}
