package qa.udst.ecommerce.model;

public class DigitalProduct extends AbstractProduct {
    String downloadLink;

    public DigitalProduct(int id, String name, double price, String downloadLink) {
        super(id, name, price);
        downloadLink = this.downloadLink;
    }

    @Override
    public void displayDetails() {
        System.out.println("Digital Product: " + getName() + ", Price: " + getPrice());
    }
}
