package dev.modusami.invmanagementsys.service;

import dev.modusami.invmanagementsys.enums.Status;
import dev.modusami.invmanagementsys.model.InventoryItemId;
import dev.modusami.invmanagementsys.model.InventoryItem;
import dev.modusami.invmanagementsys.model.Product;

import java.util.HashMap;
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

    public InventoryItem getItem(InventoryItemId id) {
        if (inventory.containsKey(id)) {
            return inventory.get(id);
        }
        return null;
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
