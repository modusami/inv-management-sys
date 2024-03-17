package dev.modusami.invmanagementsys.model;
import java.time.LocalDate;
import java.time.Month;


/**
 * Represents a single inventory item that will be placed in
 * * @author Michael-Andre Odusami
 *  @version 2023.01.30
 */
public class InventoryItem {
    private Product product;
    private int dayOrdered;
    private int monthOrdered;
    private int yearOrdered;

    /**
     * Generates the current Date and sets the order dates
     */
    private void generateDate() {
        LocalDate date = LocalDate.now();
        setMonthOrdered(date.getMonth());
        setDayOrdered(date.getDayOfMonth());
        setYearOrdered(date.getYear());

    }

    /**
     * Sets the month ordered
     */
    private void setMonthOrdered(Month month) {
        if (month != null) {
            monthOrdered = month.getValue();
        }
        else {
            monthOrdered = 0;
        }
    }
    /**
     * Sets the month that has been ordered
     */
    private void setDayOrdered(int day) {
        if (day >= 1 && day <= 31) {
            dayOrdered = day;
        }
    }
    /**
     * Sets the year that has been ordered
     */
    private void setYearOrdered(int year) {
        yearOrdered = year;
    }


    /**
     * Default Constructor
     */
    public InventoryItem() {
        this(new Product());
    }

    /**
     * Constructor
     * @param p a Product
     */
    public InventoryItem(Product p) {
        product = p;
        generateDate();
    }

    /**
     * Gets DayOrdered
     * @return dayOrdered
     */
    public int getDayOrdered() {
        return dayOrdered;
    }

    /**
     * Gets Month Ordered
     * @return monthOrdered
     */
    public int getMonthOrdered() {
        return monthOrdered;
    }

    /**
     * Gets yearOrdered
     * @return yearOrdered
     */
    public int getYearOrdered() {
        return yearOrdered;
    }

    /**
     * Gets Product
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets a product
     * @param p a product
     */
    public void setProduct(Product p) {
        product = p;
    }

    /**
     * Equals Method
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() == obj.getClass()) {
            InventoryItem temp = (InventoryItem) obj;
            return this.product.equals(temp.getProduct());
        }
        return false;
    }

    /**
     * To String method
     */
    @Override
    public String toString() {
        String s = "Date Ordered:" + toDate() + "\n";
        return s + product.toString();
    }

    private String toDate() {
        return " " + monthOrdered + "/" + dayOrdered + "/" + yearOrdered;
    }

    public static void main(String[] args) {
        InventoryItem i = new InventoryItem(new Product());
        System.out.println(i.toString());
    }
}
