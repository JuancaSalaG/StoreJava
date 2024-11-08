package store.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import store.models.ProductInventory;

public interface ProductService {
    public String createProduct(String categoryName, Optional<String> categoryDescription, String name, String price, String stock, String expiration);
    public String updateProduct(String code, Optional<String> categoryName, Optional<String> categoryDescription, Optional<String> name, Optional<String> price, Optional<String> stock, Optional<String> expiration);
    public String deleteProduct(String code);
    public Optional<Object> getProductByCode(String code);
    public ArrayList<ProductInventory> searchProducts(Optional<String> name, Optional<String> catName);
}
