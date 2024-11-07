package store.models;

public class ListItem {
    private String productCode;
    private String productName;
    private double price;
    private int quantity;
    private double total;

    public ListItem(String productCode, String productName, double price, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
    }

    public String toString() {
        return String.format("Product: %s - %s Price: $%.2f Quantity: %d Total: $%.2f\n", productCode, productName, price, quantity, total);
    }
}
