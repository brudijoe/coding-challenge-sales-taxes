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
        Item item1 = new Item("Item1", 10.0, false, false);
        Item item2 = new Item("Item2", 20.0, false, false);

        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);

        assertEquals(29.83, shoppingCart.calculateTotal(shoppingCart.getItems()));
    }

}
