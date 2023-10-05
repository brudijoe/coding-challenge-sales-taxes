package com.github.brudijoe;

import com.github.brudijoe.item.Item;
import com.github.brudijoe.shoppingCart.ShoppingCart;
import com.github.brudijoe.utils.InputParser;

import java.util.Scanner;

public class SalesTaxApplication {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the shop!");

        boolean buying = true;
        while (buying) {

            System.out.println("Please type what you like to buy e.g. '1 imported book at 12.49'");

            String input = scanner.nextLine().toLowerCase();
            System.out.println();

            Item newItem = InputParser.parseInput(input);
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

}
