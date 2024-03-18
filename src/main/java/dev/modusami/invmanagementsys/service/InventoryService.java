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

    public void populateInventory(){
        // Create Product instances
        Product product1 = new Product("Laptop", "Electronics", "High-performance laptop with SSD storage", 999.99);
        Product product2 = new Product("Smartphone", "Electronics", "Latest smartphone model with high-resolution camera", 799.99);
        Product product3 = new Product("Headphones", "Electronics", "Wireless headphones with noise-cancellation feature", 199.99);
        Product product4 = new Product("Backpack", "Fashion", "Durable backpack suitable for daily use", 49.99);
        Product product5 = new Product("Running Shoes", "Sports", "Comfortable running shoes with advanced cushioning technology", 129.99);
        Product product6 = new Product("Running Shoes", "Sports (Rebook)", "Comfortable running shoes with advanced cushioning technology", 111.99);
        Product product7 = new Product("Running Shoes", "Sports (Nike)", "Comfortable running shoes with advanced cushioning technology", 1111.99);
        // Create InventoryItem instances
        InventoryItem item1 = new InventoryItem(product1, new InventoryItemId());
        InventoryItem item2 = new InventoryItem(product2, new InventoryItemId());
        InventoryItem item3 = new InventoryItem(product3, new InventoryItemId());
        InventoryItem item4 = new InventoryItem(product4, new InventoryItemId());
        InventoryItem item5 = new InventoryItem(product5, new InventoryItemId());
        InventoryItem item6 = new InventoryItem(product6, new InventoryItemId());
        InventoryItem item7 = new InventoryItem(product7, new InventoryItemId());

        // Add items to inventory HashMap
        inventory.put(item1.getId(), item1);
        inventory.put(item2.getId(), item2);
        inventory.put(item3.getId(), item3);
        inventory.put(item4.getId(), item4);
        inventory.put(item5.getId(), item5);
        inventory.put(item6.getId(), item6);
        inventory.put(item7.getId(), item7);
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

}
