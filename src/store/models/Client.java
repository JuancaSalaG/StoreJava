package store.models;

import java.sql.Date;
import store.base.Entity;

public class Client extends Entity {
    private String address;
    private int orders;
    private Date lastOrderDate;

    public Client(String name, String phone, String address) {
        super(TypeEntity.PERSON, name, phone);
        this.address = address;
        this.orders = 0;
    }

    public String toString() {
        return "Client: \n" + super.toString() + " Address: " + address + " Orders: " + orders + " Last order date: " + lastOrderDate + "\n";
    }
}
