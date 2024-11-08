package store.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import store.interfaces.ProductDAO;
import store.models.ProductInventory;

public class ProductDAOImpl implements ProductDAO {    
    private ArrayList<ProductInventory> products = new ArrayList<ProductInventory>();

    public ProductDAOImpl() {
        String dateString = "2025-01-01"; 
        String dateString2 = "2025-03-15"; 
        String dateString3 = "2025-06-20"; 
        String dateString4 = "2025-02-22"; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateString);
            Date date2 = dateFormat.parse(dateString2);
            Date date3 = dateFormat.parse(dateString3);
            Date date4 = dateFormat.parse(dateString4);

            products.add(new ProductInventory("Food", "Any type of food", "rfsT1", "Apple", 250.0d, 100, date));
            products.add(new ProductInventory("Food", "Any type of food", "abY25", "Meat", 310.0d, 100, date2));
            products.add(new ProductInventory("Cleaning", "Products for cleaning", "zcTAC", "Shampoo", 59.9d, 100, date3));
            products.add(new ProductInventory("Pharmacy", "Products for healthy", "yYdD4", "C Vitamin", 103.2d, 100, date4));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createProduct(ProductInventory product) {
        products.add(product);
    }

    @Override
    public void updateProduct(int index, ProductInventory product) {
        products.set(index, product);       
    }

    @Override
    public int getIndex(String code) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void deleteProduct(int index) {
        products.remove(index);        
    }

    @Override
    public ProductInventory getProductByCode(String code) {
        for (ProductInventory productInventory : products) {
            if (productInventory.getCode().equals(code)) {
                return productInventory;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ProductInventory> searchProducts(Optional<String> name, Optional<String> catName) {
        String nameValue = name.isPresent() ? name.get() : "";
        String catNameValue = catName.isPresent() ? catName.get() : "";

        ArrayList<ProductInventory> result = new ArrayList<ProductInventory>();
        for (ProductInventory productInventory : products) {
            if (productInventory.getName().toLowerCase().contains(nameValue.toLowerCase()) && !nameValue.isEmpty()) {
                result.add(productInventory);                
            }
            if (productInventory.getCategoryName().toLowerCase().contains(catNameValue.toLowerCase()) && !catNameValue.isEmpty()) {
                result.add(productInventory);                
            }
        }
        return result;
    }

    @Override
    public ArrayList<ProductInventory> getAllProducts() {
        return products;
    }    
}
