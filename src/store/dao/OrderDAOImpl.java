package store.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import store.interfaces.OrderDAO;
import store.models.Order;
import store.models.StatusOrder;

public class OrderDAOImpl implements OrderDAO {
    HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

    @Override
    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order getOrder(int id) {
        return orders.get(id);
    }

    @Override
    public HashMap<Integer, Order> searchOrders(Optional<Integer> clientId, Optional<StatusOrder> status, Optional<Date> createdAt) {
        int clientIdInt = clientId.isPresent() ? clientId.get() : -1;
        StatusOrder statusOrder = status.isPresent() ? status.get() : null;
        Date createdAtDate = createdAt.isPresent() ? createdAt.get() : null;

        HashMap<Integer, Order> result = new HashMap<Integer, Order>();
        for (Order order : orders.values()) {
            if (clientIdInt > -1 && order.getCustomerId() == clientIdInt) {                
                result.put(order.getId(), order);
            }
            if (statusOrder != null && order.getStatus() == statusOrder) {
                result.put(order.getId(), order);
            }
            if (createdAtDate != null && order.getCreatedAt().compareTo(createdAtDate) == 0) {
                result.put(order.getId(), order);
            }
        }
        return result;
    }

    @Override
    public void updateOrder(int id, Order order) {
        orders.put(id, order);
    }

    @Override
    public int getOrderLastId() {
        ArrayList<Integer> keys = new ArrayList<Integer>(orders.keySet());
        if (keys.size() == 0) {
            return 0;
        }
        return keys.get(keys.size() - 1);
    }

    @Override
    public HashMap<Integer, Order> getAllOrders() {
        return orders;
    }
}
