package store.dao;

import java.util.HashMap;

import store.interfaces.OrderDAO;
import store.models.Order;

public class OrderDAOImpl {
    HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
}
