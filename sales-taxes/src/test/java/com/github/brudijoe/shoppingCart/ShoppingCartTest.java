package com.github.brudijoe.shoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.brudijoe.item.Item;

public class ShoppingCartTest {

    ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testAddItem() {
        Item item1 = new Item("Item1", 10.0, false, false);
        Item item2 = new Item("Item2", 20.0, false, false);

        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);

        assertEquals(2, shoppingCart.getItems().size());
    }

    @Test
    public void testCalculateTotal() {
        Item book = new Item("book", 12.49, false, true);
        Item cd = new Item("music CD", 14.99, false, false);
        Item chocolateBar = new Item("chocolate bar", 0.85, false, true);

        shoppingCart.addItem(book);
        shoppingCart.addItem(cd);
        shoppingCart.addItem(chocolateBar);

        assertEquals(29.83, shoppingCart.calculateTotal(shoppingCart.getItems()));
    }

    @Test
    public void testCalculateSalesTaxes() {
        Item book = new Item("book", 12.49, false, true);
        Item cd = new Item("music CD", 14.99, false, false);
        Item chocolateBar = new Item("chocolate bar", 0.85, false, true);

        shoppingCart.addItem(book);
        shoppingCart.addItem(cd);
        shoppingCart.addItem(chocolateBar);

        assertEquals(1.50, shoppingCart.calculateSalesTaxes(shoppingCart.getItems()));
    }

}
