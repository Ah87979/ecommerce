package qa.udst.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import qa.udst.ecommerce.model.DigitalProduct;
import qa.udst.ecommerce.model.PhysicalProduct;
import qa.udst.ecommerce.service.ProductService;

@Slf4j
@Component
public class EcommerceRunner implements CommandLineRunner {

    public ProductService productService;

    public EcommerceRunner(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Ecommerce application is starting...");
        log.info("Testing Lombok-refactored code...");
        // Create example objects
        log.debug("Debugging mode: Initializing data.");
        PhysicalProduct laptop = new PhysicalProduct(1, "Laptop", 1500.0, 2.5);
        DigitalProduct ebook = new DigitalProduct(2, "E-Book", 20.0, "https://example.com/download");
        // Test getters
        log.info("Laptop Name: " + laptop.getName());
        log.info("E-Book Price: " + ebook.getPrice());
        // Test setters
        laptop.setPrice(1400.0);
        log.info("Updated Laptop Price: " + laptop.getPrice());
        // Test toString
        log.info("Laptop Details: " + laptop.toString());
        // Test equals
        PhysicalProduct anotherLaptop = new PhysicalProduct(1, "Laptop", 1400.0, 2.5);
        log.info("Are laptops equal? " + laptop.equals(anotherLaptop));
        log.info("Ecommerce application finished execution.");

        productService.addProduct(laptop);
        productService.addProduct(ebook);

    }
    
}
