package dev.jacksd.iprwc.api.controller;

import dev.jacksd.iprwc.api.DTO.ProductDTO;
import dev.jacksd.iprwc.api.Service.ProductService;
import dev.jacksd.iprwc.api.model.Product;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("api/v1/products")
@RestController
public class ProductController {

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
            List<ProductDTO> productDTOs = productService.getProducts()
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createProducts(@RequestBody List<Product> product) {
            productService.saveAll(product);
            return new ResponseEntity<>("Products added.", HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {

        if (productService.getProductById(id).isPresent()) {
            ProductDTO productDTO = convertToDto(productService.getProductById(id).orElseThrow());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


     private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}