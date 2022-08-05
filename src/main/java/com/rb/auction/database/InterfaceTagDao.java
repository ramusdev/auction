package com.rb.auction.database;

import com.rb.auction.model.Tag;

import java.util.List;
import java.util.Optional;

public interface InterfaceTagDao {
    void add(Tag tag);
    List<Tag> getAll();
    Optional<Tag> getBySlug(String slug);
}
