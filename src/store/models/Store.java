package store.models;
import store.base.Entity;

public class Store extends Entity {
    private String city;

    public Store(String name, String phone, String city) {
        super(TypeEntity.STORE, name, phone);
        this.city = city;        
    }

    public String toString() {
        return "Information: " + super.toString() + " City: " + city;
    }

    @Override
    public String getName() {
        return "Store ➡️ " + super.getName();
    }
}
