package qa.udst.ecommerce.model;

public class PhysicalProduct extends AbstractProduct implements Shippable {
    private double weight;

    public PhysicalProduct(int id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    @Override
    public double calculateShippingCost() {
        return weight * 2;
    }

    @Override
    public String generateTrackingNumber() {
        return "TRACK-" + getId();
    }

    @Override
    public void displayDetails() {
        System.out.println("Physical Product: " + getName() + ", Price: " + getPrice() + ", Weight: " + weight);
    }
}
