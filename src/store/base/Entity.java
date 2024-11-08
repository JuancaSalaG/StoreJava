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

    public String toString() {
        return "\nType: " + type + "\nName: " + name + "\nPhone: " + phone;
    }

    public String getName() {
        return name;
    }
}
