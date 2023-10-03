package com.github.brudijoe;

import com.github.brudijoe.item.Item;
import com.github.brudijoe.shoppingCart.ShoppingCart;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalesTaxApplication {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the shop!");

        boolean buying = true;
        while (buying) {

            System.out.println("Please type what you like to buy e.g. '1 imported book at 12.49'");

            String input = scanner.nextLine();
            System.out.println();

            Item newItem = parseInput(input);
            if (newItem != null) {
                shoppingCart.addItem(newItem);
                shoppingCart.printShoppingCartReceipt();
            } else {
                System.out.println("Input does not match the expected format.");
            }

            System.out.println("\nWould you like to buy another item? (yes/no)");
            String response = scanner.nextLine();
            if (response.equals("no")) {
                buying = false;
            }
        }

        scanner.close();
    }

    private static Item parseInput(String input) {
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
