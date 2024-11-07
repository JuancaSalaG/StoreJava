package store.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import store.models.*;

public interface OrderDAO {
    public void addOrder(Order order);
    public void getOrder(int id);
    public HashMap<Integer, Order> searchOrders(Optional<Integer> clientId, Optional<String> clientName, Optional<StatusOrder> status, Optional<Date> createdAt);
    public void updateOrder(Optional<StatusOrder> status, int id, Optional<Integer> clientId, Optional<String> cientAddress, Optional<ArrayList<ListItem>> products);
    public void listOrders(HashMap<Integer, Order> orders);
    public double calculateTotal(ArrayList<ListItem> products);
}
