package com.rb.auction.model.view;

import com.rb.auction.model.Tag;

import java.util.Arrays;

public class TagView extends Tag {
    private String[] tagField;

    public TagView(int id, String name, String slug, String[] tagField) {
        super(id, name, slug);
        this.tagField = tagField;
    }

    public TagView(String[] tagField) {
        this.tagField = tagField;
    }

    public TagView() {

    }

    public String[] getTagField() {
        return tagField;
    }

    public void setTagField(String[] tagField) {
        this.tagField = tagField;
    }

    @Override
    public String toString() {
        return "TagView{" +
                "tagField=" + Arrays.toString(tagField) +
                '}';
    }
}
