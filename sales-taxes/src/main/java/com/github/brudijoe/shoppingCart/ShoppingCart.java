package com.github.brudijoe.shoppingCart;

import java.util.ArrayList;

import com.github.brudijoe.item.Item;

public class ShoppingCart {
    private ArrayList<Item> items;
    private double total;
    private double salesTaxes;

    public ShoppingCart() {
        items = new ArrayList<>();
        total = 0;
        salesTaxes = 0;
    }

    public void addItem(Item item) {
        items.add(item);
        updateCart(items);
    }

    private void updateCart(ArrayList<Item> arrayList) {
        total = calculateTotal(arrayList);
        salesTaxes = calculateSalesTaxes(arrayList);
    }

    public double getTotal() {
        return total;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double calculateTotal(ArrayList<Item> arrayList) {
        double total = 0;
        return total;
    }

    public double calculateSalesTaxes(ArrayList<Item> arrayList) {
        double salesTaxes = 0;
        return salesTaxes;
    }
}
