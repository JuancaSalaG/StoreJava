package store.interfaces;

import java.util.ArrayList;
import java.util.Optional;

import store.models.ProductInventory;

public interface ProductDAO {
    public void createProduct(ProductInventory product);
    public void updateProduct(int index, ProductInventory product);
    public void deleteProduct(int index);
    public ProductInventory getProductByCode(String code);
    public ArrayList<ProductInventory> getAllProducts();
    public int getIndex(String code);
    public ArrayList<ProductInventory> searchProducts(Optional<String> name, Optional<String> catName);
}
