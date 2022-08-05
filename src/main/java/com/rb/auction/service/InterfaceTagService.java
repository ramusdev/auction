package com.rb.auction.service;

import com.rb.auction.model.Tag;

import java.util.List;
import java.util.Optional;

public interface InterfaceTagService {
    void add(Tag tag);
    List<Tag> getAll();
    Optional<Tag> getBySlug(String tagSlug);
}
