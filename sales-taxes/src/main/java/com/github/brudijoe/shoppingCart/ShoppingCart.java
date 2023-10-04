package com.github.brudijoe.shoppingCart;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

import com.github.brudijoe.item.Item;

/**
 * Represents a shopping cart that can hold items and calculate the total cost, including sales
 * taxes, of the items in the cart.
 */
public class ShoppingCart {
    private ArrayList<Item> items;
    private double totalWithoutSalesTaxes;
    private double salesTaxes;
    private double total;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalWithoutSalesTaxes = 0;
        salesTaxes = 0;
        total = 0;
    }

    public void addItem(Item item) {
        items.add(item);
        updateCart(items);
    }

    private void updateCart(ArrayList<Item> arrayList) {
        totalWithoutSalesTaxes = calculateTotalWithoutSalesTaxes(arrayList);
        salesTaxes = calculateSalesTaxes(arrayList);
        total = totalWithoutSalesTaxes + salesTaxes;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /*
     * Round to 2 decimal places.
     */
    public double roundTotalWithoutSalesTaxes(double totalWithoutSalesTaxes) {
        return Math.round(totalWithoutSalesTaxes * 100.0) / 100.0;
    }

    /**
     * Calculates the total cost of items in the cart without including sales taxes.
     *
     * @param arrayList The list of items in the cart.
     * @return The total cost of items without sales taxes.
     */
    public double calculateTotalWithoutSalesTaxes(ArrayList<Item> arrayList) {
        double totalWithoutSalesTaxes = 0;
        for (Item item : arrayList) {
            totalWithoutSalesTaxes += item.getPrice() * item.getQuantity();
        }
        return roundTotalWithoutSalesTaxes(totalWithoutSalesTaxes);
    }

    /**
     * Round up to the nearest of 0.05.
     *
     * @param salesTax The sales tax without rounding.
     * @return The sales tax rounded.
     */
    public double roundSalesTax(double salesTax) {
        return Math.ceil(salesTax * 20.0) / 20.0;
    }

    /**
     * Returns the taxrate, dependent on booleans from item.
     * 
     * 0% Taxrate on food, medicine and books. 10% Taxrate on normal goods. 5% Taxrate on imported
     * goods, no exceptions.
     * 
     * @param item The current item.
     * @return The taxrate.
     */
    public int determineTaxRate(Item item) {
        return 0 + (item.isImported() ? 5 : 0) + (item.isExemptFromTaxes() ? 0 : 10);
    }

    /**
     * Calculates the taxrate, dependent on item quanitiy and price.
     * 
     * @param taxRate The current taxrate for the current item.
     * @param item The current item.
     * @return The sales tax.
     */
    public double calculateSalesTax(int taxRate, Item item) {
        return (taxRate * item.getPrice() * item.getQuantity()) / 100;
    }

    /**
     * Calculates the total sales taxes for the items in the cart.
     *
     * @param arrayList The list of items in the cart.
     * @return The total sales taxes.
     */
    public double calculateSalesTaxes(ArrayList<Item> arrayList) {
        double salesTaxes = 0;
        for (Item item : arrayList) {
            int taxRate = determineTaxRate(item);
            double salesTax = calculateSalesTax(taxRate, item);
            salesTaxes += roundSalesTax(salesTax);
        }
        return salesTaxes;
    }

    /**
     * Prints a receipt for the items in the shopping cart, including quantities, item names,
     * prices, sales taxes, and the total cost.
     */
    public void printShoppingCartReceipt() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", symbols);

        for (Item item : items) {
            System.out.println(item.getQuantity() + " " + (item.isImported() ? "imported " : "")
                    + item.getName() + ": " + df.format(item.getPrice()));
        }
        System.out.println("Sales Taxes: " + df.format(salesTaxes));
        System.out.println("Total: " + (df.format(total)));
    }
}
