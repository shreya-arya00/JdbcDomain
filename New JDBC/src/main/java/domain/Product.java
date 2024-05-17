package domain;

// Product class representing individual products
public class Product {
    final Integer id;
    final String name;
    private double price;

    public Product(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter methods
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setter methods
    public void setPrice(double price) {
        this.price = price;
    }
}
