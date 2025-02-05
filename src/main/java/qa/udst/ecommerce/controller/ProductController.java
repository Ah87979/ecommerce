package qa.udst.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.udst.ecommerce.model.AbstractProduct;
import qa.udst.ecommerce.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<AbstractProduct> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public AbstractProduct findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }
    
    @PostMapping
    public void addProduct(@RequestBody AbstractProduct product) {        
        productService.addProduct(product.getId(), product.getName(), product.getPrice());
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody AbstractProduct product) {
        productService.updateProduct(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.removeProduct(id);
    }

    @GetMapping("/{id}/tracking")
    public String getMethodName(@PathVariable int id) {
        return new String();
    }
    
}
