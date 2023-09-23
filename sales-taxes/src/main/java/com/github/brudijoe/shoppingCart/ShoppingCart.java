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
        total = calculateTotalWithoutSalesTaxes(arrayList);
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

    public double calculateTotalWithoutSalesTaxes(ArrayList<Item> arrayList) {
        double total = 0;
        for (Item item : arrayList) {
            double price = item.getPrice();
            int quantity = item.getQuantity();
            total += price * quantity;
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public double calculateSalesTaxes(ArrayList<Item> arrayList) {
        double salesTaxes = 0;
        return salesTaxes;
    }
}
