package com.github.brudijoe.item;

import java.math.BigDecimal;

public class Item {
    private int quantity = 1;
    private String name;
    private BigDecimal price;
    private int taxRate;

    public Item(int quantity, String name, BigDecimal price, int taxRate) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getTaxRate() {
        return this.taxRate;
    }

    @Override
    public String toString() {
        return "{" + " quantity='" + getQuantity() + "'" + ", name='" + getName() + "'"
                + ", price='" + getPrice() + "'" + ", taxRate='" + getTaxRate() + "'" + "}";
    }

}
