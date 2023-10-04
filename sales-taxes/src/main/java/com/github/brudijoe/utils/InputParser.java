package com.github.brudijoe.utils;

import com.github.brudijoe.item.Item;

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
        Pattern pattern = Pattern.compile("(^\\d+)(\\D+)(\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            int quantity = Integer.parseInt(matcher.group(1));
            String itemDescription = matcher.group(2).toLowerCase();
            double itemPrice = Double.parseDouble(matcher.group(3));

            String[] itemRegexPatterns = {"book", "music CD", "chocolate bar", "box of chocolates",
                    "bottle of perfume", "packet of headache pills"};

            ArrayList<String> exemptFromTaxes = new ArrayList<>();
            exemptFromTaxes.add("book");
            exemptFromTaxes.add("chocolate bar");
            exemptFromTaxes.add("box of chocolates");
            exemptFromTaxes.add("packet of headache pills");

            String itemName = "";
            boolean isImported = false;
            boolean exemptFromTaxesBoolean = false;

            if (itemDescription.contains("imported")) {
                isImported = true;
            }

            for (String patternStr : itemRegexPatterns) {
                if (itemDescription.contains(patternStr)) {
                    itemName = patternStr;
                    if (exemptFromTaxes.contains(itemName)) {
                        exemptFromTaxesBoolean = true;
                    }
                    break;
                }
            }

            return new Item(quantity, itemName, itemPrice, isImported, exemptFromTaxesBoolean);
        }

        return null;
    }
}
