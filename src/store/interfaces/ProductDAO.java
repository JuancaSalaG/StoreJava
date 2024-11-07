package store.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import store.models.ProductInventory;

public interface ProductDAO {
    public void createProduct(String catName, Optional<String> catDesciption, String name, double price, int stock, Date expiration);
    public void updateProduct(String code, Optional<String> name, Optional<Double> price, Optional<Integer> stock, Optional<Date> expiration);
    public void deleteProduct(String code);
    public void listProducts(ArrayList<ProductInventory> products);
    public void getProductByCode(String code);
    public void searchProducts(Optional<String> name, Optional<String> catName);
    public String generateCode();
}
