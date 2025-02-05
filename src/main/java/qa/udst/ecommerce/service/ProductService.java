package qa.udst.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import qa.udst.ecommerce.model.*;

@Service
public class ProductService {

    private List<AbstractProduct> products = new ArrayList<>();

    public List<AbstractProduct> getAllProducts() {
        return new ArrayList<>(this.products); // Return a copy to prevent external modifications
    }

    public AbstractProduct findProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found!"));
    }

    public AbstractProduct addProduct(int id, String name, double price, ProductCategory category, String extraValue) {
        AbstractProduct product;

        switch (category) {
            case DIGITAL:
                product = new DigitalProduct(id, name, price, extraValue); // extraValue is download link
                break;

            case PHYSICAL:
                double weight;
                try {
                    weight = Double.parseDouble(extraValue); // Parse extraValue as weight
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid weight value for physical product.");
                }
                product = new PhysicalProduct(id, name, price, weight);
                break;

            case SERVICE:
                throw new UnsupportedOperationException("Service products are not yet implemented.");

            default:
                throw new IllegalArgumentException("Invalid product category.");
        }

        product.setId(products.size() + 1); // Assign unique ID
        products.add(product);
        return product;
    }

    public AbstractProduct updateProduct(int id, String name, double price) {
        AbstractProduct product = findProductById(id);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    public void removeProduct(int id) {
        boolean removed = products.removeIf(product -> product.getId() == id);
        if (!removed) {
            throw new ProductNotFoundException("Cannot remove: Product with ID " + id + " not found!");
        }
    }

    public List<AbstractProduct> getProductsByCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> {
                    if (category == ProductCategory.DIGITAL && product instanceof DigitalProduct) return true;
                    if (category == ProductCategory.PHYSICAL && product instanceof PhysicalProduct) return true;
                    return false; // SERVICE category is not implemented
                })
                .collect(Collectors.toList());
    }
}
