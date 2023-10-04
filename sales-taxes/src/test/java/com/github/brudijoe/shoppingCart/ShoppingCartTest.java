package com.github.brudijoe.shoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.brudijoe.item.Item;

public class ShoppingCartTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    ShoppingCart shoppingCart1;
    ShoppingCart shoppingCart2;
    ShoppingCart shoppingCart3;

    @BeforeEach
    public void setUp() {
        shoppingCart1 = new ShoppingCart();
        shoppingCart2 = new ShoppingCart();
        shoppingCart3 = new ShoppingCart();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private void initializeShoppingCartWithTestDataOne() {
        Item book = new Item(1, "Book", 12.49, false, true);
        Item cd = new Item(1, "Music CD", 14.99, false, false);
        Item chocolateBar = new Item(1, "Chocolate bar", 0.85, false, true);

        shoppingCart1.addItem(book);
        shoppingCart1.addItem(cd);
        shoppingCart1.addItem(chocolateBar);
    }

    private void initializeShoppingCartWithTestDataTwo() {
        Item importedBoxOfChocolates = new Item(1, "Box of chocolates", 10.00, true, true);
        Item importedBottleOfPerfume = new Item(1, "Bottle of perfume", 47.50, true, false);

        shoppingCart2.addItem(importedBoxOfChocolates);
        shoppingCart2.addItem(importedBottleOfPerfume);
    }

    private void initializeShoppingCartWithTestDataThree() {
        Item importedBottleOfPerfume = new Item(1, "Bottle of perfume", 27.99, true, false);
        Item bottleOfPerfume = new Item(1, "Bottle of perfume", 18.99, false, false);
        Item packetOfHeadachePills = new Item(1, "Packet of headache pills", 9.75, false, true);
        Item importedBoxOfChocolates = new Item(1, "Box of chocolates", 11.25, true, true);

        shoppingCart3.addItem(importedBottleOfPerfume);
        shoppingCart3.addItem(bottleOfPerfume);
        shoppingCart3.addItem(packetOfHeadachePills);
        shoppingCart3.addItem(importedBoxOfChocolates);
    }

    @Test
    public void testAddItems() {
        initializeShoppingCartWithTestDataOne();
        assertEquals(3, shoppingCart1.getItems().size());

        initializeShoppingCartWithTestDataTwo();
        assertEquals(2, shoppingCart2.getItems().size());

        initializeShoppingCartWithTestDataThree();
        assertEquals(4, shoppingCart3.getItems().size());
    }

    @Test
    public void testCalculateTotalWithoutSalesTaxes() {
        initializeShoppingCartWithTestDataOne();
        assertEquals(28.33,
                shoppingCart1.calculateTotalWithoutSalesTaxes(shoppingCart1.getItems()));

        initializeShoppingCartWithTestDataTwo();
        assertEquals(57.50,
                shoppingCart2.calculateTotalWithoutSalesTaxes(shoppingCart2.getItems()));

        initializeShoppingCartWithTestDataThree();
        assertEquals(67.98,
                shoppingCart3.calculateTotalWithoutSalesTaxes(shoppingCart3.getItems()));
    }

    @Test
    public void testRoundSalesTax() {
        assertEquals(1.85, shoppingCart1.roundSalesTax(1.82));
        assertEquals(1.9, shoppingCart1.roundSalesTax(1.87));
        assertEquals(1.7, shoppingCart1.roundSalesTax(1.699999999999999));
    }

    @Test
    public void testDetermineTaxrate() {
        Item normalItem = new Item(1, "item", 12.45, false, false);
        assertEquals(10, shoppingCart1.determineTaxRate(normalItem));
        Item importedNormalItem = new Item(1, "book", 12.45, true, false);
        assertEquals(15, shoppingCart1.determineTaxRate(importedNormalItem));
        Item excemptItem = new Item(1, "item", 12.45, false, true);
        assertEquals(0, shoppingCart1.determineTaxRate(excemptItem));
        Item importedExcemptItem = new Item(1, "book", 12.45, true, true);
        assertEquals(5, shoppingCart1.determineTaxRate(importedExcemptItem));
    }

    @Test
    public void testCalculateSalesTax() {
        Item normalItem = new Item(1, "item", 14.99, false, false);
        assertEquals(1.499, shoppingCart1.calculateSalesTax(10, normalItem));
    }

    /*
     * Precision issues with floating point numbers.
     */
    private static final double DELTA = 1e-15;

    @Test
    public void testCalculateSalesTaxes() {
        initializeShoppingCartWithTestDataOne();
        assertEquals(1.50, shoppingCart1.calculateSalesTaxes(shoppingCart1.getItems()), DELTA);

        initializeShoppingCartWithTestDataTwo();
        assertEquals(7.65, shoppingCart2.calculateSalesTaxes(shoppingCart2.getItems()), DELTA);

        initializeShoppingCartWithTestDataThree();
        assertEquals(6.70, shoppingCart3.calculateSalesTaxes(shoppingCart3.getItems()), DELTA);
    }

    @Test
    public void testGetTotal() {
        initializeShoppingCartWithTestDataOne();
        assertEquals(29.83, shoppingCart1.getTotal());

        initializeShoppingCartWithTestDataTwo();
        assertEquals(65.15, shoppingCart2.getTotal());

        initializeShoppingCartWithTestDataThree();
        assertEquals(74.68, shoppingCart3.getTotal());
    }

    @Test
    public void printShoppingCartReceipt() {
        initializeShoppingCartWithTestDataOne();
        shoppingCart1.printShoppingCartReceipt();
        assertEquals(
                "1 Book: 12.49\r\n1 Music CD: 14.99\r\n1 Chocolate bar: 0.85\r\nSales Taxes: 1.50\r\nTotal: 29.83",
                outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
