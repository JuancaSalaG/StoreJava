package store.models;

import java.util.Date;
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

    public ProductInventory(String categoryName, String code, String name, double price, int stock, Date expiration) {
        super(categoryName);
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.expiration = expiration;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setCategoryName(String name) {
        super.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public String getCategoryName() {
        return super.getName();
    }

    public String toString() {
        return super.toString() + "Product: \nCode: " + code + " Name: " + name + " Price: " + price + " Stock: " + stock + " Expiration: " + expiration + "\n";
    }    
}