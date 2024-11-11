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

    public Client(String name, String adress, int id) {
        super(TypeEntity.PERSON, name);
        this.id = id;
        this.address = adress;
        this.orders = 0;
    }

    public Client(Client clientCopy) {
        super(TypeEntity.PERSON, clientCopy.getName(), clientCopy.getPhone());
        this.id = clientCopy.id;
        this.address = clientCopy.address;
        this.orders = clientCopy.orders;
        this.lastOrderDate = clientCopy.lastOrderDate;
    }

    public String toString() {
        return "Client: " + super.toString() + "\nId: " + id + " Address: " + address + " Orders: " + orders + " Last order date: " + lastOrderDate + "\n";
    }

    public void setName(String name) {
        super.setName(name);
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return super.getPhone();
    }

    public void serPhone(String phone) {
        super.setPhone(phone);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClientOrder() {
        this.orders += 1;
        this.lastOrderDate = new Date(System.currentTimeMillis());
    }
}
