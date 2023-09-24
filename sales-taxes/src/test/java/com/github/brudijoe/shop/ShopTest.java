package com.github.brudijoe.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.brudijoe.item.Item;

public class ShopTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Shop shop;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testPrintCategoryNames() {
        shop.printCategoryNames();
        assertEquals("(1) Food\r\n(2) Medicine\r\n(3) Books\r\n(4) Music\r\n(5) Drugstore",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGetItemsInCategory() {
        List<Item> foodItems = shop.getItemsInCategory(0);
        assertEquals(3, foodItems.size());
        assertEquals("Chocolate Bar", foodItems.get(0).getName());
        assertEquals("Box of Chocolates", foodItems.get(1).getName());
        assertEquals("Box of Chocolates", foodItems.get(2).getName());

        List<Item> medicineItems = shop.getItemsInCategory(1);
        assertEquals(1, medicineItems.size());
        assertEquals("Packet of Headache Pills", medicineItems.get(0).getName());

        List<Item> booksItems = shop.getItemsInCategory(2);
        assertEquals(1, booksItems.size());
        assertEquals("Book", booksItems.get(0).getName());

        List<Item> musicItems = shop.getItemsInCategory(3);
        assertEquals(1, musicItems.size());
        assertEquals("Music CD", musicItems.get(0).getName());

        List<Item> drugstoreItems = shop.getItemsInCategory(4);
        assertEquals(2, drugstoreItems.size());
        assertEquals("Bottle of Perfume", drugstoreItems.get(0).getName());
        assertEquals("Bottle of Perfume", drugstoreItems.get(1).getName());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
