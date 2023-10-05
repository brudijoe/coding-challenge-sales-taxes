package com.github.brudijoe.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import com.github.brudijoe.item.Item;

public class InputParserTest {

    @Test
    public void testParseInputValid() {
        String input = "2 imported bottle of perfume at 27.99";
        Item item = InputParser.parseInput(input);

        assertNotNull(item);
        assertEquals(2, item.getQuantity());
        assertEquals("bottle of perfume", item.getName());
        assertEquals(BigDecimal.valueOf(27.99), item.getPrice());
        assertEquals(15, item.getTaxRate());
    }

    @Test
    public void testParseInputInvalid() {
        String input = "Invalid input";
        Item item = InputParser.parseInput(input);

        assertNull(item);
    }

    @Test
    public void testParseInputExemptFromTaxes() {
        String input = "1 book at 12.49";
        Item item = InputParser.parseInput(input);

        assertNotNull(item);
        assertEquals(1, item.getQuantity());
        assertEquals("book", item.getName());
        assertEquals(BigDecimal.valueOf(12.49), item.getPrice());
        assertEquals(0, item.getTaxRate());
    }

    @Test
    public void testParseInputImported() {
        String input = "1 imported box of chocolates at 10.00";
        Item item = InputParser.parseInput(input);

        assertNotNull(item);
        assertEquals(1, item.getQuantity());
        assertEquals("box of chocolates", item.getName());
        assertEquals(BigDecimal.valueOf(10.00), item.getPrice());
        assertEquals(5, item.getTaxRate());
    }
}
