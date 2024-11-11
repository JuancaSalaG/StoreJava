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
    private double totalOrder;

    public Order(int id, int customerId, String clientAddress, ArrayList<ListItem> products, double totalOrder) {
        this.id = id;
        this.createdAt = new Date(System.currentTimeMillis());
        this.status = StatusOrder.PENDING;
        this.customerId = customerId;
        this.clientAddress = clientAddress;
        this.listItems = new ArrayList<>(products);
        this.totalOrder = totalOrder;
    }

    public Order(Order orderCopy) {
        this.id = orderCopy.id;
        this.createdAt = orderCopy.createdAt;
        this.status = orderCopy.status;
        this.customerId = orderCopy.customerId;
        this.clientAddress = orderCopy.clientAddress;
        this.listItems = new ArrayList<>(orderCopy.listItems);
        this.totalOrder = orderCopy.totalOrder;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public String toString() {
        return "Order: \nId: " + id + " Created at: " + createdAt + " Status: " + status + " Customer id: " + customerId + " Client address: " + clientAddress + " List items: " + listItems + " Total Order: " + totalOrder + "\n";
    }
}
