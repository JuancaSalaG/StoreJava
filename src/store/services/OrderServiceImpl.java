package store.services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

import store.dao.ClientDAOImpl;
import store.dao.OrderDAOImpl;
import store.interfaces.OrderService;
import store.interfaces.PaymentMethod;
import store.models.ListItem;
import store.models.Order;
import store.models.ProductInventory;
import store.models.StatusOrder;

public class OrderServiceImpl implements OrderService {
    private final OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
    private final ProductServiceImpl productService = new ProductServiceImpl();
    private final ClientDAOImpl clientDAOImpl = ClientDAOImpl.getInstance();
    private ArrayList<ListItem> items = new ArrayList<ListItem>();

    @Override
    public String addOrder(String clientId, String clientAddress, ArrayList<ListItem> products) {
        int clientIdInt;
        
        try {
            clientIdInt = Integer.parseInt(clientId);
        } catch (Exception e) {
            return "Invalid client ID";
        }

        int orderId = getLastId() + 1;
        double totalOrder = getTotalOrder();

        Order order = new Order(orderId, clientIdInt, clientAddress, products, totalOrder);
        orderDAOImpl.addOrder(order);
        clientDAOImpl.addOrderToClient(clientIdInt);
        items.clear();
        return null;
    }

    @Override
    public Order getOrder(int id) {
        return orderDAOImpl.getOrder(id);
    }

    @Override
    public HashMap<Integer, Order> searchOrders(Optional<String> clientId, Optional<String> status,
            Optional<String> createdAt) {
        String createdAtStr = createdAt.isPresent() ? createdAt.get() : "";
        String clientIdStr = clientId.isPresent() ? clientId.get() : "";
        String statusStr = status.isPresent() ? status.get() : "";

        if (clientIdStr.isEmpty() && statusStr.isEmpty() && createdAtStr.isEmpty()) {
            return orderDAOImpl.getAllOrders();            
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAtDate = null;
        int clientIdInt = -1;
        int statusOrder;
        if (!clientIdStr.isEmpty()) {
            try {
                clientIdInt = Integer.parseInt(clientId.get());
            } catch (Exception e) {
                return null;
            }
        }
        
        if (!createdAtStr.isEmpty()) {
            try {
                createdAtDate = dateFormat.parse(createdAtStr);
            } catch (java.text.ParseException e) {
                return null;
            }
        }
        
        StatusOrder statusOrderEnum = null;
        if (!statusStr.isEmpty()) {
            try {
                statusOrder = Integer.parseInt(status.get());
            } catch (Exception e) {
                return null;
            }
            switch (statusOrder) {
                case 1:
                    statusOrderEnum = StatusOrder.PENDING;
                    break;
                case 2:
                    statusOrderEnum = StatusOrder.SHIPPED;
                    break;
                case 3:
                    statusOrderEnum = StatusOrder.DELIVERED;
                    break;
                case 4:
                    statusOrderEnum = StatusOrder.CANCELLED;
                    break;
                case 5:
                    statusOrderEnum = StatusOrder.RETURNED;
                    break;
                case 6:
                    statusOrderEnum = StatusOrder.PAID;
                    break;
                default:
                    statusOrderEnum = StatusOrder.PENDING;
                    break;
            }            
        }
        return orderDAOImpl.searchOrders(Optional.ofNullable(clientIdInt), Optional.ofNullable(statusOrderEnum), Optional.ofNullable(createdAtDate));
    }

    @Override
    public String updateOrder(int id, int status) {
        PaymentMethod nequiPayment = new NequiPayment();
        PaymentMethod paypalPayment = new PayPalPayment();

        Scanner scanner = new Scanner(System.in);
        Order copyOrder = new Order(getOrder(id));
        if (status == 6) {
            System.out.println("Please choice a payment method: ");
            System.out.println("1. Nequi");
            System.out.println("2. PayPal");
            int paymentMethod = scanner.nextInt();
            if (paymentMethod == 1) {
                nequiPayment.processingPay(copyOrder.getTotalOrder());
            } else {
                paypalPayment.processingPay(copyOrder.getTotalOrder());
            }
        }

        StatusOrder statusOrderEnum;
        switch (status) {
            case 1:
                statusOrderEnum = StatusOrder.PENDING;
                break;
            case 2:
                statusOrderEnum = StatusOrder.SHIPPED;
                break;
            case 3:
                statusOrderEnum = StatusOrder.DELIVERED;
                break;
            case 4:
                statusOrderEnum = StatusOrder.CANCELLED;
                break;
            case 5:
                statusOrderEnum = StatusOrder.RETURNED;
                break;
            case 6:
                statusOrderEnum = StatusOrder.PAID;
                break;
            default:
                statusOrderEnum = StatusOrder.PENDING;
                break;
        }
        copyOrder.setStatus(statusOrderEnum);
        orderDAOImpl.updateOrder(id, copyOrder);

        return null;
    }

    @Override
    public String addProduct(String productCode, Integer quantity) {
        Optional<Object> product = productService.getProductByCode(productCode);
        ProductInventory productInv = (ProductInventory) product.get();

        productInv.setStock(productInv.getStock() - quantity);
        ListItem listItem = new ListItem(productCode, productInv.getName(), productInv.getPrice(), quantity);
        items.add(listItem);
        return "Product added to the order";
    }

    @Override
    public ArrayList<ListItem> getItems() {
        return items;
    }

    private int getLastId() {
        return orderDAOImpl.getOrderLastId();
    }

    private double getTotalOrder() {
        double total = 0;
        for (ListItem item : items) {
            total += item.getTotal();
        }
        return total;
    }
}
