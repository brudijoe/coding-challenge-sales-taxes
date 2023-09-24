package com.github.brudijoe.shop;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.brudijoe.item.Item;
import com.github.brudijoe.item.ItemCategory;

/**
 * Represents a shop that contains categories of items available for purchase.
 */
public class Shop {

    private List<ItemCategory> categories;

    public Shop() {
        this.categories = new ArrayList<>();
        initializeCategories();
    }

    private void initializeCategories() {
        ItemCategory foodCategory = createFoodCategory();
        ItemCategory medicineCategory = createMedicineCategory();
        ItemCategory bookCategory = createBookCategory();
        ItemCategory musicCategory = createMusicCategory();
        ItemCategory drugstoreCategory = createDrugstoreCategory();

        categories.add(foodCategory);
        categories.add(medicineCategory);
        categories.add(bookCategory);
        categories.add(musicCategory);
        categories.add(drugstoreCategory);
    }

    public List<ItemCategory> getCategories() {
        return this.categories;
    }

    private ItemCategory createFoodCategory() {
        ItemCategory foodCategory = new ItemCategory("Food");
        foodCategory.addItem(new Item("Chocolate Bar", 0.85, false, true));
        foodCategory.addItem(new Item("Box of Chocolates", 10.00, true, true));
        foodCategory.addItem(new Item("Box of Chocolates", 11.25, true, true));
        return foodCategory;
    }

    private ItemCategory createMedicineCategory() {
        ItemCategory medicineCategory = new ItemCategory("Medicine");
        medicineCategory.addItem(new Item("Packet of Headache Pills", 9.75, false, true));
        return medicineCategory;
    }

    private ItemCategory createBookCategory() {
        ItemCategory bookCategory = new ItemCategory("Books");
        bookCategory.addItem(new Item("Book", 12.49, false, true));
        return bookCategory;
    }

    private ItemCategory createMusicCategory() {
        ItemCategory musicCategory = new ItemCategory("Music");
        musicCategory.addItem(new Item("Music CD", 14.99, false, false));
        return musicCategory;
    }

    private ItemCategory createDrugstoreCategory() {
        ItemCategory drugstoreCategory = new ItemCategory("Drugstore");
        drugstoreCategory.addItem(new Item("Bottle of Perfume", 27.99, true, false));
        drugstoreCategory.addItem(new Item("Bottle of Perfume", 47.50, true, false));
        return drugstoreCategory;
    }

    public void printCategoryNames() {
        for (int i = 0; i < categories.size(); i++) {
            ItemCategory category = categories.get(i);
            System.out.println("(" + (i + 1) + ") " + category.getCategoryName());
        }
    }

    public List<Item> getItemsInCategory(int categoryIndex) {
        return categories.get(categoryIndex).getItems();
    }

    
    /**
     * Prints the items in a specified item category along with their names and prices.
     *
     * @param categoryIndex The index of the category.
     */
    public void printItemsInCategory(int categoryIndex) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", symbols);

        for (int i = 0; i < categories.get(categoryIndex).getItems().size(); i++) {
            System.out.println(
                    "(" + (categories.indexOf(categories.get(i)) + 1) + ") "
                            + categories.get(categoryIndex).getItems().get(i).getName() + ": "
                            + df.format(categories.get(categoryIndex).getItems().get(i).getPrice()));
        }
    }
}
