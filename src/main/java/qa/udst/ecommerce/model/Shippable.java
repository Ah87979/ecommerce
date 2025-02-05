package qa.udst.ecommerce.model;

public interface Shippable extends Trackable {
    double calculateShippingCost();
}
