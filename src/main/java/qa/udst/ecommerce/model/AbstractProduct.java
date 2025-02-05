package qa.udst.ecommerce.model;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractProduct {
    private int id;
    private String name;
    private double price;

    public AbstractProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    public void validateProductName() {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
    }

    public abstract void displayDetails();

}
