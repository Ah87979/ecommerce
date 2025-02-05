package qa.udst.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import qa.udst.ecommerce.model.AbstractProduct;
import qa.udst.ecommerce.model.ProductCategory;
import qa.udst.ecommerce.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
        summary = "Retrieve all products",
        description = "Fetch a list of all products available in inventory."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Products retrieved successfully", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<AbstractProduct> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(
        summary = "Retrieve product by ID",
        description = "Fetch details of a product by its unique ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public AbstractProduct findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @Operation(
        summary = "Add a new product",
        description = "Create a new product in the inventory."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public AbstractProduct addProduct(@RequestParam int id,
                                      @RequestParam String name,
                                      @RequestParam double price,
                                      @RequestParam ProductCategory category,
                                      @RequestParam String extraValue) {
        return productService.addProduct(id, name, price, category, extraValue);
    }

    @Operation(
        summary = "Update an existing product",
        description = "Update details of an existing product."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product updated successfully", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public AbstractProduct updateProduct(@PathVariable int id,
                                         @RequestBody AbstractProduct product) {
        return productService.updateProduct(id, product.getName(), product.getPrice());
    }

    @Operation(
        summary = "Delete a product",
        description = "Remove a product from the inventory by its unique ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product deleted successfully", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.removeProduct(id);
        return "Product with ID " + id + " deleted successfully.";
    }

    @Operation(
        summary = "Retrieve products by category",
        description = "Fetch all products within a specific category."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Products retrieved successfully", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "No products found in the specified category")
    })
    @GetMapping("/category/{category}")
    public List<AbstractProduct> getProductsByCategory(@PathVariable ProductCategory category) {
        return productService.getProductsByCategory(category);
    }
}

