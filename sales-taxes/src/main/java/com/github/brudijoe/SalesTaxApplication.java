package com.github.brudijoe;

import java.util.List;
import java.util.Scanner;

import com.github.brudijoe.item.Item;
import com.github.brudijoe.shop.Shop;
import com.github.brudijoe.shoppingCart.ShoppingCart;

public class SalesTaxApplication {
    public static void main(String[] args) {

        Shop shop = new Shop();
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the shop, select numbers to operate the shop.");
        boolean buying = true;
        while (buying) {
            
            System.out.println("Select a category:");
            shop.printCategoryNames();

            int selectedCategoryIndex = Integer.parseInt(scanner.nextLine()) - 1;
            List<Item> selectedCategoryItems = shop.getItemsInCategory(selectedCategoryIndex);
            System.out.println("Available items in the selected category:");
            shop.printItemsInCategory(selectedCategoryIndex);

            System.out.println("What item would you like to buy?");
            int selectedItemIndex = Integer.parseInt(scanner.nextLine()) - 1;
            Item selectedItem = selectedCategoryItems.get(selectedItemIndex);

            System.out.println("Enter the quantity:");
            int quantity = Integer.parseInt(scanner.nextLine());
            selectedItem.setQuantity(quantity);

            shoppingCart.addItem(selectedItem);

            System.out.println("Would you like to buy another item?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            String response = scanner.nextLine();
            if (!response.equals("1")) {
                buying = false;
            }
        }

        shoppingCart.printShoppingCartReceipt();

        scanner.close();
    }
}
