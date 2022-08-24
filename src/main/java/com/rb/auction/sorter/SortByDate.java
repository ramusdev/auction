package com.rb.auction.sorter;

import com.rb.auction.model.MessageItem;

import java.util.Comparator;

public class SortByDate implements Comparator<MessageItem>{
    @Override
    public int compare(MessageItem messageItemOne, MessageItem messageItemTwo) {
        if (messageItemOne.getDate().isAfter(messageItemTwo.getDate())) {
            return 1;
        } else if (messageItemOne.getDate().isBefore(messageItemTwo.getDate())){
            return -1;
        } else {
            return 0;
        }
    }
}

