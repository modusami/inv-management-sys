package dev.modusami.invmanagementsys.model;
import dev.modusami.invmanagementsys.exceptions.InvalidPriceException;

import java.text.DecimalFormat;
/**
 * Represents a single product to be put in the inventory
 * @author Michael-Andre Odusami
 * @version 2024.01.29
 */
public class Product {
    private String name;
    private String category;
    private String description;
    private double price;

    public Product(){
        this("", "", "", 0.0);
    }

    public Product(String s, String c, String d, double p){
        name = s;
        category = c;
        description = d;
        price = p;
    }

    /**
     * Getter for the product name
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     */
    public void setName(String str) {
        if (str != null) {
            name = str;
        }
    }


    /**
     * Getter for the category name
     * @return the category name
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for the category name
     * @return the category name
     */
    public void setCategory(String str) {
        if (str != null) {
            category = str;
        }
    }

    /**
     * Getter for the description name
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description name
     * @return the description name
     */
    public void setDescription(String str) {
        if (str != null) {
            description = str;
        }
    }

    /**
     * Getter for the price
     * @return the price
     */
    public double getPrice() {
        return price;
    }


    /**
     * Setter for the price
     */
    public void setPrice(double pr) {
        if (pr > 0.0) {
            price = pr;
        }
        else{
            throw new InvalidPriceException("Invalid Price");
        }
    }

    /**
     * Equals method
     * @param obj to compare to a product
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() == obj.getClass()) {
            Product temp = (Product)obj;
            boolean isEqual = (this.name == temp.getName()) &&
                    (this.category == temp.getCategory()) &&
                    (this.price == temp.getPrice());
            return isEqual;
        }
        return false;
    }

    /**
     * Converts a product to a string representation
     * @return string representation
     */
    @Override
    public String toString() {
        String s = "";
        s += ("Name: " + this.name + "\n");
        s += ("Category: " + this.category + "\n");
        s += ("Description: " + this.description + "\n");
        DecimalFormat df = new DecimalFormat("0.00");
        s += ("Price: $" + df.format(this.price) + "\n");
        return s;
    }

}
