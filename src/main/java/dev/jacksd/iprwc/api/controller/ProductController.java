package dev.jacksd.iprwc.api.controller;

import dev.jacksd.iprwc.api.Service.ProductService;
import dev.jacksd.iprwc.api.model.Product;
import dev.jacksd.iprwc.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/v1/products")
@RestController
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public void createProducts(@RequestBody List<Product> product) {
        productService.addNewProducts(product);
    }
}
