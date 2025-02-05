package qa.udst.ecommerce.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import qa.udst.ecommerce.model.AbstractProduct;
import qa.udst.ecommerce.model.ProductNotFoundException;

@Service
public class ProductService {

    private ArrayList<AbstractProduct> products = new ArrayList<>();
    
    public ArrayList<AbstractProduct> getAllProducts() {
        return this.products;
    }

    public AbstractProduct findProductById(int id) {
        for (AbstractProduct product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found!");
    }

    public void addProduct(int id, String name, double price) {
        id = products.size() + 1;
        
    }

    public void updateProduct(int id) {
        
    }

    public void removeProduct(int id) {
        for (AbstractProduct product: products) {
            if (product.getId() == id) {
                this.products.remove(product);
            }
        }
    }

}
