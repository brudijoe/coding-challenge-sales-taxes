package com.github.brudijoe.shoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
                Item book = new Item(1, "Book", BigDecimal.valueOf(12.49), 0);
                Item cd = new Item(1, "Music CD", BigDecimal.valueOf(14.99), 10);
                Item chocolateBar = new Item(1, "Chocolate bar", BigDecimal.valueOf(0.85), 0);

                shoppingCart1.addItem(book);
                shoppingCart1.addItem(cd);
                shoppingCart1.addItem(chocolateBar);
        }

        private void initializeShoppingCartWithTestDataTwo() {
                Item importedBoxOfChocolates =
                                new Item(1, "Box of chocolates", BigDecimal.valueOf(10.00), 5);
                Item importedBottleOfPerfume =
                                new Item(1, "Bottle of perfume", BigDecimal.valueOf(47.50), 15);

                shoppingCart2.addItem(importedBoxOfChocolates);
                shoppingCart2.addItem(importedBottleOfPerfume);
        }

        private void initializeShoppingCartWithTestDataThree() {
                Item importedBottleOfPerfume =
                                new Item(1, "Bottle of perfume", BigDecimal.valueOf(27.99), 15);
                Item bottleOfPerfume =
                                new Item(1, "Bottle of perfume", BigDecimal.valueOf(18.99), 10);
                Item packetOfHeadachePills = new Item(1, "Packet of headache pills",
                                BigDecimal.valueOf(9.75), 0);
                Item importedBoxOfChocolates =
                                new Item(1, "Box of chocolates", BigDecimal.valueOf(11.25), 5);

                shoppingCart3.addItem(importedBottleOfPerfume);
                shoppingCart3.addItem(bottleOfPerfume);
                shoppingCart3.addItem(packetOfHeadachePills);
                shoppingCart3.addItem(importedBoxOfChocolates);
        }

        @Test
        public void testSizeShoppingCarts() {
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
                assertEquals(BigDecimal.valueOf(28.33), shoppingCart1
                                .calculateTotalWithoutSalesTaxes(shoppingCart1.getItems()));

                initializeShoppingCartWithTestDataTwo();
                assertEquals(BigDecimal.valueOf(57.50).setScale(2, RoundingMode.UNNECESSARY),
                                shoppingCart2.calculateTotalWithoutSalesTaxes(
                                                shoppingCart2.getItems()));

                initializeShoppingCartWithTestDataThree();
                assertEquals(BigDecimal.valueOf(67.98), shoppingCart3
                                .calculateTotalWithoutSalesTaxes(shoppingCart3.getItems()));
        }

        @Test
        public void testRoundSalesTax() {
                assertEquals(BigDecimal.valueOf(1.85),
                                shoppingCart1.roundSalesTax(BigDecimal.valueOf(1.82)));
                assertEquals(BigDecimal.valueOf(1.90).setScale(2, RoundingMode.UNNECESSARY),
                                shoppingCart1.roundSalesTax(BigDecimal.valueOf(1.87)));
                assertEquals(BigDecimal.valueOf(1.70).setScale(2, RoundingMode.UNNECESSARY),
                                shoppingCart1.roundSalesTax(BigDecimal.valueOf(1.699999999999999)));
        }

        @Test
        public void testCalculateSalesTax() {
                Item normalItem = new Item(1, "item", BigDecimal.valueOf(14.99), 10);
                assertEquals(BigDecimal.valueOf(1.499),
                                shoppingCart1.calculateSalesTax(10, normalItem));
        }

        @Test
        public void testCalculateSalesTaxes() {
                initializeShoppingCartWithTestDataOne();
                assertEquals(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.UNNECESSARY),
                                shoppingCart1.calculateSalesTaxes(shoppingCart1.getItems()));

                initializeShoppingCartWithTestDataTwo();
                assertEquals(BigDecimal.valueOf(7.65),
                                shoppingCart2.calculateSalesTaxes(shoppingCart2.getItems()));

                initializeShoppingCartWithTestDataThree();
                assertEquals(BigDecimal.valueOf(6.70).setScale(2, RoundingMode.UNNECESSARY),
                                shoppingCart3.calculateSalesTaxes(shoppingCart3.getItems()));
        }

        @Test
        public void testGetTotal() {
                initializeShoppingCartWithTestDataOne();
                assertEquals(BigDecimal.valueOf(29.83), shoppingCart1.getTotal());

                initializeShoppingCartWithTestDataTwo();
                assertEquals(BigDecimal.valueOf(65.15), shoppingCart2.getTotal());

                initializeShoppingCartWithTestDataThree();
                assertEquals(BigDecimal.valueOf(74.68), shoppingCart3.getTotal());
        }

        @Test
        public void printShoppingCartReceipt() {
                initializeShoppingCartWithTestDataOne();
                shoppingCart1.printShoppingCartReceipt();
                assertEquals("1 Book: 12.49\r\n1 Music CD: 14.99\r\n1 Chocolate bar: 0.85\r\nSales Taxes: 1.50\r\nTotal: 29.83",
                                outputStreamCaptor.toString().trim());
        }

        @AfterEach
        public void tearDown() {
                System.setOut(standardOut);
        }

}
