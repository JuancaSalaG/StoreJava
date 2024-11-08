package store.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import store.interfaces.ProductService;
import store.models.ProductInventory;
import store.dao.ProductDAOImpl;

public class ProductServiceImpl implements ProductService {
    private final ProductDAOImpl productDAOImpl = new ProductDAOImpl();

    @Override
    public String createProduct(String categoryName, Optional<String> categoryDescription, String name, String price, String stock, String expiration) {
        String description = categoryDescription.isPresent() ? categoryDescription.get() : "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        int stockValue = 0;
        double priceValue = 0.0d;
        
        try {
            date = dateFormat.parse(expiration);
            priceValue = Double.parseDouble(price);
            stockValue = Integer.parseInt(stock);
        } catch (ParseException e) {
            return "Date";
        }catch (NumberFormatException e) {
            return "Number";
        }

        if (priceValue < 0 || stockValue < 0) {
            return "Negative";            
        }
        if (priceValue == 0.0 || stockValue == 0) {
            return "Zero";            
        }

        String code = generateProductCode();
        ProductInventory product = null;
        if (description.isEmpty()) {
            product = new ProductInventory(categoryName, name, code, priceValue, stockValue, date);
        } else {
            product = new ProductInventory(categoryName, description, code, name, priceValue, stockValue, date);
        }
        productDAOImpl.createProduct(product);
        return code;
    }

    @Override
    public String updateProduct(String code, Optional<String> categoryName, Optional<String> categoryDescription, Optional<String> name, Optional<String> price, Optional<String> stock, Optional<String> expiration) {
        String description = categoryDescription.isPresent() ? categoryDescription.get() : "";
        String catName = categoryName.isPresent() ? categoryName.get() : "";
        String productName = name.isPresent() ? name.get() : "";
        String priceValue = price.isPresent() ? price.get() : "";
        String stockValue = stock.isPresent() ? stock.get() : "";
        String expirationValue = expiration.isPresent() ? expiration.get() : "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        int stockInt = 0;
        double priceDouble = 0.0d;
        if (!expirationValue.isEmpty()) {
            try {
                date = dateFormat.parse(expirationValue);
            } catch (ParseException e) {
                return "Date";
            }
        }
        if (!stockValue.isEmpty()) {
            try {
                stockInt = Integer.parseInt(stockValue);
            }catch (NumberFormatException e) {
                return "Number";
            } 
            if (stockInt < 0) {
                return "Negative";            
            }
            if (stockInt == 0) {
                return "Zero";            
            }  
        }
        if (!priceValue.isEmpty()) {
            try {
                priceDouble = Double.parseDouble(priceValue);
            }catch (NumberFormatException e) {
                return "Number";
            }
            if (priceDouble < 0) {
                return "Negative";            
            }
            if (priceDouble == 0.0) {
                return "Zero";            
            }             
        }    

        Optional<Object> optProduct = getProductByCode(code);
        if (optProduct.isPresent() && optProduct.get() instanceof String) {
            return optProduct.get().toString();
        }

        ProductInventory product = (ProductInventory) optProduct.get();
        if (!productName.isEmpty()) {
            product.setName(productName);
        }
        if (!description.isEmpty()) {
            product.setDescription(description);            
        }
        if (!catName.isEmpty()) {
            product.setCategoryName(catName);            
        }
        if (!priceValue.isEmpty()) {
            product.setPrice(priceDouble);            
        }
        if (!stockValue.isEmpty()) {
            product.setStock(stockInt);            
        }
        if (!expirationValue.isEmpty()) {
            product.setExpiration(date);            
        }
        int index = productDAOImpl.getIndex(code);
        productDAOImpl.updateProduct(index, product);        
        return code;
    }

    @Override
    public String deleteProduct(String code) {
        Optional<Object> optProduct = getProductByCode(code);
        if (optProduct.isPresent() && optProduct.get() instanceof String) {
            return optProduct.get().toString();
        }
        int index = productDAOImpl.getIndex(code);
        productDAOImpl.deleteProduct(index);
        return "Product deleted successfully!!";
    }

    @Override
    public Optional<Object> getProductByCode(String code) {
        if (code.isEmpty()) {
            return Optional.of("Code");
        }
        if (code.length() != 5) {
            return Optional.of("Length");            
        }
        ProductInventory product = productDAOImpl.getProductByCode(code);
        if (product == null) {
            return Optional.of("Product");
        }        
        return Optional.of(product);
    }

    @Override
    public ArrayList<ProductInventory> searchProducts(Optional<String> name, Optional<String> catName) {
        String productName = name.isPresent() ? name.get() : "";
        String categoryName = catName.isPresent() ? catName.get() : "";
        if (productName.isEmpty() && categoryName.isEmpty()) {
            return productDAOImpl.getAllProducts();            
        }
        ArrayList<ProductInventory> listProducts = productDAOImpl.searchProducts(name, catName);
        return listProducts;
    }

    private String generateProductCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
