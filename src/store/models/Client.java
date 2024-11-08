package store.models;

import java.sql.Date;
import store.base.Entity;

public class Client extends Entity {
    private int id;
    private String address;
    private int orders;
    private Date lastOrderDate;

    public Client(String name, String phone, String address, int id) {
        super(TypeEntity.PERSON, name, phone);
        this.id = id;
        this.address = address;
        this.orders = 0;
    }

    public String toString() {
        return "Client: \n" + super.toString() + "Id: " + id + " Address: " + address + " Orders: " + orders + " Last order date: " + lastOrderDate + "\n";
    }
}
