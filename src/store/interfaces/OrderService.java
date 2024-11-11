package store.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import store.models.ListItem;
import store.models.Order;

public interface OrderService {
    public String addOrder(String clientId, String clientAddress, ArrayList<ListItem> products);
    public Order getOrder(int id);
    public String addProduct(String peroductCode, Integer quantity);
    public HashMap<Integer, Order> searchOrders(Optional<String> clientId, Optional<String> status, Optional<String> createdAt);
    public String updateOrder(int id, int status);
    public ArrayList<ListItem> getItems();
}
