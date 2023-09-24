package com.github.brudijoe.item;

import java.util.ArrayList;
import java.util.List;

public class ItemCategory {
    private String categoryName;
    private List<Item> items;

    public ItemCategory(String categoryName) {
        this.categoryName = categoryName;
        this.items = new ArrayList<>();
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

}
