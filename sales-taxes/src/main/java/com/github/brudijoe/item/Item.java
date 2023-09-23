package com.github.brudijoe.item;

public class Item {
    private String name;
    private double price;
    private boolean imported;
    private boolean exemptFromTaxes;
    private int quantity = 1;

    public Item(String name, double price, boolean imported, boolean exemptFromTaxes) {
        this.name = name;
        this.price = price;
        this.imported = imported;
        this.exemptFromTaxes = exemptFromTaxes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isImported() {
        return this.imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public boolean isExemptFromTaxes() {
        return this.exemptFromTaxes;
    }

    public void setExemptFromTaxes(boolean exemptFromTaxes) {
        this.exemptFromTaxes = exemptFromTaxes;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", price='" + getPrice() + "'" +
                ", imported='" + isImported() + "'" +
                ", exemptFromTaxes='" + isExemptFromTaxes() + "'" +
                ", quantity='" + getQuantity() + "'" +
                "}";
    }

}
