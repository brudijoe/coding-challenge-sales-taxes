package com.github.brudijoe.utils;

import com.github.brudijoe.item.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The InputParser class provides methods to parse user input strings into Item objects. It extracts
 * item quantity, description, price, and other attributes from input strings following a specific
 * format.
 */
public class InputParser {

    /**
     * Parses a user input string and creates an Item object from it.
     *
     * @param input The user input string in the format "quantity description at price."
     * @return An Item object representing the parsed input, or null if the input format is invalid.
     */
    public static Item parseInput(String input) {
        Matcher matcher = createInputMatcher(input);

        if (matcher.matches()) {
            int quantity = Integer.parseInt(matcher.group(1));
            String itemDescription = matcher.group(2).toLowerCase();
            double itemPrice = Double.parseDouble(matcher.group(3));
            String itemName = determineItemName(itemDescription);
            int taxRate = calculateTaxRate(itemDescription, itemName);

            return new Item(quantity, itemName, BigDecimal.valueOf(itemPrice), taxRate);
        }

        return null;
    }

    private static Matcher createInputMatcher(String input) {
        Pattern pattern = Pattern.compile("(^\\d+)(\\D+)(\\d+(\\.\\d+)?)");
        return pattern.matcher(input);
    }

    private static String determineItemName(String itemDescription) {
        String[] itemRegexPatterns = {"book", "music CD", "chocolate bar", "box of chocolates",
                "bottle of perfume", "packet of headache pills"};

        for (String patternStr : itemRegexPatterns) {
            if (itemDescription.contains(patternStr)) {
                return patternStr;
            }
        }

        return "";
    }

    private static int calculateTaxRate(String itemDescription, String itemName) {
        int taxRate = 0;

        if (itemDescription.contains("imported")) {
            taxRate += 5;
        }

        if (!itemName.isEmpty()) {
            ArrayList<String> exemptFromTaxes = new ArrayList<>();
            exemptFromTaxes.add("book");
            exemptFromTaxes.add("chocolate bar");
            exemptFromTaxes.add("box of chocolates");
            exemptFromTaxes.add("packet of headache pills");

            if (!exemptFromTaxes.contains(itemName)) {
                taxRate += 10;
            }
        }

        return taxRate;
    }
}
