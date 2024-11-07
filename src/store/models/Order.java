package store.models;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
    private int id;
    private Date createdAt;
    private StatusOrder status;
    private int customerId;
    private String clientAddress;
    private ArrayList<ListItem> listItems;

    public Order(int id, Date createdAt, int customerId, String clientAddress, ArrayList<ListItem> products, int quantity) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = StatusOrder.PENDING;
        this.customerId = customerId;
        this.clientAddress = clientAddress;
        this.listItems = products;
    }

    public String toString() {
        return "Order: \nId: " + id + " Created at: " + createdAt + " Status: " + status + " Customer id: " + customerId + " Client address: " + clientAddress + " List items: " + listItems + "\n";
    }
}
