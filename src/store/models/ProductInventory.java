package store.models;

import java.sql.Date;
import store.base.Category;

public class ProductInventory extends Category {
    private String code;
    private String name;
    private double price;
    private int stock;
    private Date expiration;

    public ProductInventory(String categoryName, String categoryDescription, String code, String name, double price, int stock, Date expiration) {
        super(categoryName, categoryDescription);
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.expiration = expiration;
    }

    public ProductInventory(String categoryName, String code, String name, double price, int stock) {
        super(categoryName);
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        return "Product: \n" + super.toString() + " Code: " + code + " Name: " + name + " Price: " + price + " Stock: " + stock + " Expiration: " + expiration + "\n";
    }
}