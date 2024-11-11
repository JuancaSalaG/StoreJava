package store.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

import store.interfaces.PaymentMethod;
import store.models.ListItem;
import store.models.Order;
import store.services.NequiPayment;
import store.services.OrderServiceImpl;
import store.services.PayPalPayment;

public class SalesUtils {
    private static final OrderServiceImpl orderService = new OrderServiceImpl();
    private static final ProductUtils productUtils = new ProductUtils();

    public static String processCreate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCreating a new order");
        System.out.println("Please enter the order information:");
        System.out.println("Client ID:");
        String clientId = scanner.nextLine();
        System.out.println("Client Address:");
        String clientAddress = scanner.nextLine();

        System.out.println("Please enter the product IDs and quantities");
        Boolean finishAdd = false;

        while (!finishAdd) {
            System.out.println("Product information:");
            String productSearch = productUtils.processSearch();

            if (productSearch.equals("\nNo products found!!") ) {
                System.out.println("No products found!!");
                continue;
            }

            System.out.println("Product code:");
            String productCode = scanner.nextLine();
            System.out.println("Quantity:");
            int quantity = scanner.nextInt();

            String result = orderService.addProduct(productCode, quantity);
            System.out.println(result);
            scanner.nextLine();
            System.out.println("Do you want to add more products? (yes/no)");
            String addMore = scanner.nextLine();
            if (addMore.equals("no")) {
                finishAdd = true;
            }            
        }

        ArrayList<ListItem> products = orderService.getItems();
        orderService.addOrder(clientId, clientAddress, products);
        return "Order created successfully!";
    }

    public static String processSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSearching orders");
        System.out.println("Please enter the client id:");
        String clientId = scanner.nextLine();
        System.out.println("Please choice a status: ");
        System.out.println("1. Pending");
        System.out.println("2. Shipped");
        System.out.println("3. Delivered");
        System.out.println("4. Canceled");
        System.out.println("5. Returned");
        System.out.println("6. Paid");
        String statusOption = scanner.nextLine();
        System.out.println("Please enter the creation date: (Format: yyyy-MM-dd - e.g. 2024-01-01)");
        String createdAt = scanner.nextLine();
        
        HashMap<Integer, Order> orders = orderService.searchOrders(Optional.ofNullable(clientId),Optional.ofNullable(statusOption), Optional.ofNullable(createdAt));
        if (orders == null) {
            return "No orders found!!";
        }
        if (orders.size() <= 0) {
            return "No orders found!!";            
        }
        printSales(orders);
        return "\nOrders found successfully!";
    }

    public static String processGet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nGetting an order");
        System.out.println("Please enter the order id:");
        int id = scanner.nextInt();
        Order order = orderService.getOrder(id);
        if (order == null) {
            return "Order not found!!";
        }
        System.out.println(order);
        return "\nOrder found successfully!";
    }

    public static String processUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUpdating an order");
        System.out.println("Please enter the order id:");
        int id = scanner.nextInt();

        System.out.println("Please choice a status: ");
        System.out.println("1. Pending");
        System.out.println("2. Shipped");
        System.out.println("3. Delivered");
        System.out.println("4. Canceled");
        System.out.println("5. Returned");
        System.out.println("6. Paid");
        int status = scanner.nextInt();

        orderService.updateOrder(id, status);
        return "\n Order updated successfully!";
    }

    private static void printSales(HashMap<Integer, Order> orders) {
        for (Order order : orders.values()) {
            System.out.println(order);
        }
    }
}
