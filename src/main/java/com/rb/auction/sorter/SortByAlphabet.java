package com.rb.auction.sorter;

import com.rb.auction.model.Tag;

import java.util.Comparator;

public class SortByAlphabet implements Comparator<Tag> {
    @Override
    public int compare(Tag One, Tag Two) {
        char oneFirstLetter = One.getName().charAt(0);
        char twoFirstLetter = Two.getName().charAt(0);

        if (oneFirstLetter > twoFirstLetter) {
            return 1;
        } else if (oneFirstLetter < twoFirstLetter){
            return -1;
        } else {
            return 0;
        }
    }
}
