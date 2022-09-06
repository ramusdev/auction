package com.rb.auction.exceptions;

public class EntityDoNotExistsException extends RuntimeException {
    public EntityDoNotExistsException() {
        super("Entity error");
    }
}
