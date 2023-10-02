package com.github.brudijoe;

import java.util.Scanner;
import com.github.brudijoe.shoppingCart.ShoppingCart;

public class SalesTaxApplication {
    public static void main(String[] args) {

        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the shop, select numbers to operate the shop.");

        shoppingCart.printShoppingCartReceipt();
        scanner.close();
    }
}
