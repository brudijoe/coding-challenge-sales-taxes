package com.github.brudijoe.shoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private BigDecimal totalWithoutSalesTaxes;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalWithoutSalesTaxes = BigDecimal.ZERO;
        salesTaxes = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    public void addItem(Item item) {
        items.add(item);
        updateCart(items);
    }

    private void updateCart(ArrayList<Item> arrayList) {
        totalWithoutSalesTaxes = calculateTotalWithoutSalesTaxes(arrayList);
        salesTaxes = calculateSalesTaxes(arrayList);
        total = totalWithoutSalesTaxes.add(salesTaxes);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /*
     * Round up to 2 decimal places.
     */
    public BigDecimal roundTotalWithoutSalesTaxes(BigDecimal totalWithoutSalesTaxes) {
        return totalWithoutSalesTaxes.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the total cost of items in the cart without including sales taxes.
     *
     * @param arrayList The list of items in the cart.
     * @return The total cost of items without sales taxes.
     */
    public BigDecimal calculateTotalWithoutSalesTaxes(ArrayList<Item> arrayList) {
        BigDecimal totalWithoutSalesTaxes = BigDecimal.ZERO;
        for (Item item : arrayList) {
            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalWithoutSalesTaxes = totalWithoutSalesTaxes.add(itemTotal);
        }
        return roundTotalWithoutSalesTaxes(totalWithoutSalesTaxes);
    }

    /**
     * Round up to the nearest of 0.05.
     *
     * @param salesTax The sales tax without rounding.
     * @return The sales tax rounded.
     */
    public BigDecimal roundSalesTax(BigDecimal salesTax) {
        BigDecimal roundedSalesTax =
                salesTax.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.CEILING)
                        .multiply(BigDecimal.valueOf(0.05));
        return roundedSalesTax.setScale(2, RoundingMode.UNNECESSARY);
    }

    /**
     * Calculates the taxrate, dependent on item quanitiy and price.
     * 
     * @param taxRate The current taxrate for the current item.
     * @param item The current item.
     * @return The sales tax.
     */
    public BigDecimal calculateSalesTax(int taxRate, Item item) {
        return (item.getPrice().multiply(BigDecimal.valueOf(taxRate))
                .multiply(BigDecimal.valueOf(item.getQuantity()))).divide(BigDecimal.valueOf(100));
    }

    /**
     * Calculates the total sales taxes for the items in the cart.
     *
     * @param arrayList The list of items in the cart.
     * @return The total sales taxes.
     */
    public BigDecimal calculateSalesTaxes(ArrayList<Item> arrayList) {
        BigDecimal salesTaxes = BigDecimal.ZERO;
        for (Item item : arrayList) {
            int taxRate = item.getTaxRate();
            BigDecimal salesTax = calculateSalesTax(taxRate, item);
            salesTaxes = salesTaxes.add(roundSalesTax(salesTax));
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
            System.out.println(item.getQuantity() + " "
                    + (item.getTaxRate() == 5 || item.getTaxRate() == 15 ? "imported " : "")
                    + item.getName() + ": " + df.format(item.getPrice()));
        }
        System.out.println("Sales Taxes: " + df.format(salesTaxes));
        System.out.println("Total: " + (df.format(total)));
    }
}
