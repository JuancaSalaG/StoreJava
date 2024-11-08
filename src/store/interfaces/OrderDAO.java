package store.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import store.models.*;

public interface OrderDAO {
    public void addOrder(Order order);
    public Order getOrder(int id);
    public HashMap<Integer, Order> searchOrders(Optional<Integer> clientId, Optional<String> clientName, Optional<StatusOrder> status, Optional<Date> createdAt);
    public void updateOrder(int id, Order order);
}
