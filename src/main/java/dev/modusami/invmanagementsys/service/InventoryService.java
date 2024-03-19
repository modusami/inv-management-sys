package dev.modusami.invmanagementsys.service;

import dev.modusami.invmanagementsys.enums.Status;
import dev.modusami.invmanagementsys.model.InventoryItemId;
import dev.modusami.invmanagementsys.model.InventoryItem;
import dev.modusami.invmanagementsys.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * A system that manages individual Inventory Items
 * @author Michael-Andre Odusami
 * @version 2024.01.31
 */
public class InventoryService {

    /**
     * Max amount of inventory items that can be stored
     */
    private final static int MAX_CAPACITY = 50;

    /**
     * the inventory - an array of InventoryItems
     */
    public static HashMap<InventoryItemId, InventoryItem> inventory = new HashMap<>();

    /**
     * Constructor
     */
    public InventoryService(){
        populateInventory();
    }



    /**
     * Gets the amount of items in inventory
     * @return the amount of times
     */
    public static int size() {
        return inventory.size();
    }


    /**
     * Gets the max capacity for the array
     * @return max capacity of inventory
     */
    public static int maxCapacity() {
        return MAX_CAPACITY;
    }


    /**
     * Adds an item to the inventory
     * @param item to be added
     * @return if the item has been added or not
     */
    public Status addItem(InventoryItem item) {
        return addItemHelper(item);
    }

    /**
     * Adds Item to Inventory (HELPER)
     * @param item to store in inventory
     * @return status of addition
     */
    private Status addItemHelper(InventoryItem item) {
        Status status = Status.FAILED;
        if (item != null) {
            inventory.put(item.getId(), item);
            status = Status.SUCCESS;
        }
        return status;
    }

    /**
     * Edits the Item's Price
     *
     * @return if the price has been edited
     */
    public Status editItemName(InventoryItemId id, String newName) {
        Status status = Status.FAILED;
        if (inventory.containsKey(id)) {
            InventoryItem item = inventory.get(id);
            Product product = item.getProduct();
            product.setName(newName);
            status = Status.SUCCESS;
        }
        return status;
    }

    /**
     * Edits an items description
     *
     * @return if the description has been edited
     */
    public Status editItemDescription(InventoryItemId id, String newDescription) {
        Status status = Status.FAILED;
        if (inventory.containsKey(id)) {
            InventoryItem item = inventory.get(id);
            Product product = item.getProduct();
            product.setDescription(newDescription);
            status = Status.SUCCESS;
        }
        return status;
    }

    /**
     * Edits an items category
     *
     * @return if the category has been edited
     */
    public Status editItemCategory(InventoryItemId id, String newCategory) {
        Status status = Status.FAILED;
        if (inventory.containsKey(id)) {
            InventoryItem item = inventory.get(id);
            Product product = item.getProduct();
            product.setCategory(newCategory);
            status = Status.SUCCESS;
        }
        return status;
    }

    public Status editItemPrice(InventoryItemId id, double price) {
        Status status = Status.FAILED;
        if (inventory.containsKey(id)) {
            InventoryItem item = inventory.get(id);
            Product product = item.getProduct();
            product.setPrice(price);
            status = Status.SUCCESS;
        }
        return status;
    }


    /**
     * Gets a single item
     * @param id
     * @return
     */
    public InventoryItem getItem(InventoryItemId id) {
        if (inventory.containsKey(id)) {
            return inventory.get(id);
        }
        return null;
    }

    /**
     * Gets a list of items by name
     * @param name of inventory
     * @return a list of inventory items
     */
    public List<InventoryItem> getItemsByName(String name) {
        List<InventoryItem> itemsByName = new ArrayList<>();
        for (InventoryItem item : inventory.values()) {
            if (item.getProduct().getName().equalsIgnoreCase(name.toLowerCase())) {
                itemsByName.add(item);
            }
        }
        return itemsByName;
    }

    /**
     * Gets a list of items by category
     * @param name of category
     * @return a list of inventory category
     */
    public List<InventoryItem> getItemsByCategory(String name) {
        List<InventoryItem> itemsByName = new ArrayList<>();
        for (InventoryItem item : inventory.values()) {
            if (item.getProduct().getCategory().equalsIgnoreCase(name.toLowerCase())) {
                itemsByName.add(item);
            }
        }
        return itemsByName;
    }

    public HashMap<InventoryItemId, InventoryItem> getInventory() {
        return new HashMap<>(inventory);
    }


    public Status updateItem(InventoryItem item){
        Status status = Status.FAILED;
        if (containsItem(item.getId())){
            inventory.put(item.getId(), item);
            status = Status.SUCCESS;
        }

        return status;
    }

    /**
     * Responsible For Deleting an item
     * @param id in the inventory
     * @return status of deletion
     */
    public Status deleteItem(InventoryItemId id) {
        Status status = Status.FAILED;
        if (inventory.containsKey(id)) {
            inventory.remove(id);
            status = Status.SUCCESS;
        }
        return status;
    }

    /**
     * Gets Quantity Of a certain item by name
     * @param item to search for
     * @param by search item by what subject
     */
    public int quantity(String item, String by) {
        if (item == null)
            return 0;
        int amountOfItems = 0;
        // search hash-map looking for items equal to it
        for (InventoryItemId id : inventory.keySet()) {
            InventoryItem t = inventory.get(id);
            switch (by){
                case "name": {
                    if (item.equals(t.getProduct().getName())) {
                        amountOfItems++;
                    }
                }
                break;
                case "category":
                {
                    if (item.equals(t.getProduct().getCategory())) {
                        amountOfItems++;
                    }
                }
            }

        }
        return amountOfItems;
    }

    /**
     * Gets Quantity Of a certain item
     */
    public int quantity(InventoryItem item) {
        if (item == null)
            return 0;
        int amountOfItems = 0;
        // search hash-map looking for items equal to it
        for (InventoryItemId id : inventory.keySet()) {
            InventoryItem t = inventory.get(id);
            if (item.equals(t)) {
                amountOfItems++;
            }
        }
        return amountOfItems;
    }


    /**
     * Checks if an id exists in the inventory
     * @return if an item exists
     */
    public boolean containsItem(InventoryItemId id) {
        return inventory.containsKey(id);
    }

    /**
     * Clear the inventory
     */
    public void clear() {
        inventory.clear();
    }

    public void populateInventory() {
        // Create Product instances
        Product product1 = new Product("Laptop", "Electronics", "High-performance laptop with SSD storage", 999.99);
        Product product2 = new Product("Smartphone", "Electronics", "Latest smartphone model with high-resolution camera", 799.99);
        Product product3 = new Product("Headphones", "Electronics", "Wireless headphones with noise-cancellation feature", 199.99);
        Product product4 = new Product("Backpack", "Fashion", "Durable backpack suitable for daily use", 49.99);
        Product product5 = new Product("Running Shoes", "Sports", "Comfortable running shoes with advanced cushioning technology", 129.99);
        Product product6 = new Product("Running Shoes", "Sports (Reebok)", "Comfortable running shoes with advanced cushioning technology", 111.99);
        Product product7 = new Product("Running Shoes", "Sports (Nike)", "Comfortable running shoes with advanced cushioning technology", 1111.99);
        Product product8 = new Product("Smartwatch", "Electronics", "Fitness-tracking smartwatch with heart rate monitor", 299.99);
        Product product9 = new Product("Wireless Speaker", "Electronics", "Portable Bluetooth speaker with long battery life", 79.99);
        Product product10 = new Product("Digital Camera", "Electronics", "High-resolution digital camera with optical zoom", 499.99);
        Product product11 = new Product("Sunglasses", "Fashion", "Stylish sunglasses with UV protection", 89.99);
        Product product12 = new Product("Yoga Mat", "Sports", "Non-slip yoga mat with carrying strap", 39.99);
        Product product13 = new Product("Fitness Tracker", "Electronics", "Wearable fitness tracker for monitoring activity and sleep", 99.99);
        Product product14 = new Product("Bluetooth Headset", "Electronics", "Hands-free Bluetooth headset for phone calls", 59.99);
        Product product15 = new Product("Travel Backpack", "Fashion", "Lightweight backpack with multiple compartments", 69.99);
        Product product16 = new Product("Basketball", "Sports", "Official size and weight basketball", 24.99);
        Product product17 = new Product("External Hard Drive", "Electronics", "Portable external hard drive with 1TB storage", 59.99);
        Product product18 = new Product("Gaming Console", "Electronics", "Latest gaming console with 4K graphics", 499.99);
        Product product19 = new Product("Wireless Mouse", "Electronics", "Ergonomic wireless mouse for computer use", 29.99);
        Product product20 = new Product("Smartwatch", "Electronics (Apple)", "Fitness-tracking smartwatch with heart rate monitor", 399.99);

        // Create InventoryItem instances
        InventoryItem item1 = new InventoryItem(product1, new InventoryItemId());
        InventoryItem item2 = new InventoryItem(product2, new InventoryItemId());
        InventoryItem item3 = new InventoryItem(product3, new InventoryItemId());
        InventoryItem item4 = new InventoryItem(product4, new InventoryItemId());
        InventoryItem item5 = new InventoryItem(product5, new InventoryItemId());
        InventoryItem item6 = new InventoryItem(product6, new InventoryItemId());
        InventoryItem item7 = new InventoryItem(product7, new InventoryItemId());
        InventoryItem item8 = new InventoryItem(product8, new InventoryItemId());
        InventoryItem item9 = new InventoryItem(product9, new InventoryItemId());
        InventoryItem item10 = new InventoryItem(product10, new InventoryItemId());
        InventoryItem item11 = new InventoryItem(product11, new InventoryItemId());
        InventoryItem item12 = new InventoryItem(product12, new InventoryItemId());
        InventoryItem item13 = new InventoryItem(product13, new InventoryItemId());
        InventoryItem item14 = new InventoryItem(product14, new InventoryItemId());
        InventoryItem item15 = new InventoryItem(product15, new InventoryItemId());
        InventoryItem item16 = new InventoryItem(product16, new InventoryItemId());
        InventoryItem item17 = new InventoryItem(product17, new InventoryItemId());
        InventoryItem item18 = new InventoryItem(product18, new InventoryItemId());
        InventoryItem item19 = new InventoryItem(product19, new InventoryItemId());
        InventoryItem item20 = new InventoryItem(product20, new InventoryItemId());

        // Add items to inventory HashMap
        inventory.put(item1.getId(), item1);
        inventory.put(item2.getId(), item2);
        inventory.put(item3.getId(), item3);
        inventory.put(item4.getId(), item4);
        inventory.put(item5.getId(), item5);
        inventory.put(item6.getId(), item6);
        inventory.put(item7.getId(), item7);
        inventory.put(item8.getId(), item8);
        inventory.put(item9.getId(), item9);
        inventory.put(item10.getId(), item10);
        inventory.put(item11.getId(), item11);
        inventory.put(item12.getId(), item12);
        inventory.put(item13.getId(), item13);
        inventory.put(item14.getId(), item14);
        inventory.put(item15.getId(), item15);
        inventory.put(item16.getId(), item16);
        inventory.put(item17.getId(), item17);
        inventory.put(item18.getId(), item18);
        inventory.put(item19.getId(), item19);
        inventory.put(item20.getId(), item20);
    }

}
