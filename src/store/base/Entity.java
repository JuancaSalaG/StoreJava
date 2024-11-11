package store.base;
import store.models.TypeEntity;

public abstract class Entity {
    private String name;
    private String phone;
    private TypeEntity type;

    public Entity(TypeEntity type, String name, String phone) {
        this.type = type;
        this.name = name;
        this.phone = phone;
    }

    public Entity(TypeEntity type, String name) {
        this.type = type;
        this.name = name;
    }

    public String toString() {
        return "\nType: " + type + " Name: " + name + " Phone: " + phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
