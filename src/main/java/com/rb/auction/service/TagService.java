package com.rb.auction.service;

import com.rb.auction.database.InterfaceTagDao;
import com.rb.auction.model.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TagService implements InterfaceTagService {
    @Autowired
    InterfaceTagDao interfaceTagDao;

    @Override
    public void add(Tag tag) {
        this.interfaceTagDao.add(tag);
    }
}
