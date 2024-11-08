package store.utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import store.models.ProductInventory;
import store.services.ProductServiceImpl;

public class ProductUtils {
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    public static String processCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCreate a new product: ");
        System.out.println("Enter the category name: ");
        String categoryName = scanner.nextLine();
        System.out.println("Enter the category description: (optional)");
        String categoryDescription = scanner.nextLine();
        System.out.println("Enter the product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the product price: (format: 0.0 - e.g. 250.0)");
        String price = scanner.nextLine();
        System.out.println("Enter the product stock: (format: 0 - e.g. 100)");
        String stock = scanner.nextLine();
        System.out.println("Enter the product expiration date: (format: yyyy-MM-dd - e.g. 2021-12-31)");
        String expiration = scanner.nextLine();        
        
        String result = productService.createProduct(categoryName, Optional.ofNullable(categoryDescription), name, price, stock, expiration);
        if (creationError(result) != null) {
            return creationError(result);            
        }
        return "\nProduct created successfully!!";
    }

    private static String creationError(String result) {
        if (result.equals("Date")) {
            return "\nInvalid date format, please try again!!";
        }
        if (result.equals("Number")) {
            return "\nInvalid price or stock number format, please try again!!";
        }
        if (result.equals("Negative")) {
            return "\nPrice and stock must be greater than zero, please try again!!";
        }
        if (result.equals("Zero")) {
            return "\nPrice or stock must be greater than zero, please try again!!";
        }
        return null;
    }

    public static String processUpdate() {
        System.out.println("\nUpdate a product: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product code: (Format: XXXXX - e.g. abYc5)");
        String code = scanner.nextLine();
        System.out.println("Enter the category name: ");
        String categoryName = scanner.nextLine();
        System.out.println("Enter the category description: (optional)");
        String categoryDescription = scanner.nextLine();
        System.out.println("Enter the product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the product price: (format: 0.0 - e.g. 250.0)");
        String price = scanner.nextLine();
        System.out.println("Enter the product stock: (format: 0 - e.g. 100)");
        String stock = scanner.nextLine();
        System.out.println("Enter the product expiration date: (format: yyyy-MM-dd - e.g. 2021-12-31)");
        String expiration = scanner.nextLine();
        
        String result = productService.updateProduct(code, Optional.ofNullable(categoryName), Optional.ofNullable(categoryDescription), Optional.ofNullable(name), Optional.ofNullable(price), Optional.ofNullable(stock), Optional.ofNullable(expiration));
        if (creationError(result) != null) {
            return creationError(result);            
        }
        if (productErrorCode(Optional.of(result)) != null) {
            return productErrorCode(Optional.of(result));
        }
        ProductInventory product = (ProductInventory) productService.getProductByCode(code).get();
        System.out.println(product);
        return "\nProduct updated successfully!!";
    }

    public static String processGet() {
        System.out.println("\nGet product by code: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product code: (Format: XXXXX - e.g. abYc5)");
        String code = scanner.nextLine();

        Optional<Object> product = productService.getProductByCode(code);
        if (productErrorCode(product) != null) {
            return productErrorCode(product);            
        }
        System.out.println("\nProduct found: ");
        System.out.println(product.get());            
        return "Get product successfully!!";
    }

    private static String productErrorCode(Optional<Object> product) {
        if (product.get() instanceof String && product.get().equals("Code")) {
            return "\nNo code entered.";            
        }
        if (product.get() instanceof String && product.get().equals("Length")) {
            return "\nCode must have 5 characters.";            
        }
        if (product.get() instanceof String && product.get().equals("Product")) {
            return "\nProduct not found!!";            
        }
        return null;
    }

    private static void printProducts(ArrayList<ProductInventory> listProducts) {
        System.out.println("\nProducts found: ");
        for (ProductInventory product : listProducts) {
            System.out.println(product);
        }
    }

    public static String processDelete(){
        System.out.println("\nDelete product by code: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product code: (Format: XXXXX - e.g. abYc5)");
        String code = scanner.nextLine();

        String result = productService.deleteProduct(code);
        if (productErrorCode(Optional.of(result)) != null) {
            return productErrorCode(Optional.of(result));
        }
        return "\nProduct deleted successfully!!";
    }

    public static String processSearch() {
        System.out.println("\nSearch by product name and product category: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product name: ");
        String productName = scanner.nextLine();
        System.out.println("Enter the category name: ");
        String categoryName = scanner.nextLine();

        ArrayList<ProductInventory> listProducts = productService.searchProducts(Optional.ofNullable(productName), Optional.ofNullable(categoryName));
        if (listProducts.isEmpty()) {
            return "\nNo products found!!";
        }
        ProductUtils.printProducts(listProducts);
        return "\nProducts search successfully!!";
    }
}
