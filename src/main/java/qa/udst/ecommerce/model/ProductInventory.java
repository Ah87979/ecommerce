package qa.udst.ecommerce.model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductInventory {
    private HashMap<String, AbstractProduct> inventory = new HashMap<>();

    public void addProduct(String id, AbstractProduct product) {
        inventory.put(id, product);
    }

    public void addProduct(String id, AbstractProduct product, ProductCategory category) {
        product.validateProductName();
        inventory.put(id, product);
    }

    public AbstractProduct getProduct(String id) {
        if (!inventory.containsKey(id)) {
            throw new ProductNotFoundException("Product not found: " + id);
        }
        return inventory.get(id);
    }

    public List<AbstractProduct> getProductsByCategory(ProductCategory category) {
        return inventory.values().stream()
                .filter(product -> product instanceof PhysicalProduct && category == ProductCategory.PHYSICAL)
                .collect(Collectors.toList());
    }
}
