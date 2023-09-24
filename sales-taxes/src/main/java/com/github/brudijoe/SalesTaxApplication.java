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
            try {
                System.out.println("Select a category:");
                shop.printCategoryNames();

                int selectedCategoryIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (selectedCategoryIndex < 0 || selectedCategoryIndex >= shop.getCategories().size()) {
                    System.out.println("Invalid category selection. Please enter a valid number.\n");
                    continue;
                }

                List<Item> selectedCategoryItems = shop.getItemsInCategory(selectedCategoryIndex);
                System.out.println("\nAvailable items in the selected category:");
                shop.printItemsInCategory(selectedCategoryIndex);

                if (selectedCategoryItems.size() != 1) {
                    System.out.println("What item would you like to buy?");
                    int selectedItemIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (selectedItemIndex < 0 || selectedItemIndex >= selectedCategoryItems.size()) {
                        System.out.println("Invalid item selection. Please enter a valid number.\n");
                        continue;
                    }
                    Item selectedItem = selectedCategoryItems.get(selectedItemIndex);

                    System.out.println("Enter the quantity:");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    selectedItem.setQuantity(quantity);

                    shoppingCart.addItem(selectedItem);
                } else {
                    Item selectedItem = selectedCategoryItems.get(0);
                    System.out.println("Enter the quantity:");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    selectedItem.setQuantity(quantity);

                    shoppingCart.addItem(selectedItem);
                }

                System.out.println("\nWould you like to buy another item?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                String response = scanner.nextLine();
                if (!response.equals("1")) {
                    buying = false;
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
            }
        }

        shoppingCart.printShoppingCartReceipt();
        scanner.close();
    }
}
