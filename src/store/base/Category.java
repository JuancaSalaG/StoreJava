package store.base;

public abstract class Category {
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(String name) {
        this.name = name;
    }

    public String toString() {
        return "Category:  \nName: " + name + " Description: " + description + "\n";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
