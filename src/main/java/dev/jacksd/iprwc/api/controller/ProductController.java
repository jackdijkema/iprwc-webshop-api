package dev.jacksd.iprwc.api.controller;

import dev.jacksd.iprwc.api.DTO.ProductDTO;
import dev.jacksd.iprwc.api.Service.ProductService;
import dev.jacksd.iprwc.api.Service.UserService;
import dev.jacksd.iprwc.api.enums.Role;
import dev.jacksd.iprwc.api.model.Product;
import dev.jacksd.iprwc.api.model.User;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, UserService userService, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createProducts(@RequestBody List<Product> product) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Optional<User> user = userService.getUserByEmail(authenticatedUserEmail);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticatedUserEmail.equals(user.get().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if(user.get().getRole().equals(Role.CUSTOMER)) {
           return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        productService.saveAll(product);
        return new ResponseEntity<>("Products added.", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Optional<User> user = userService.getUserByEmail(authenticatedUserEmail);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticatedUserEmail.equals(user.get().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if(user.get().getRole().equals(Role.CUSTOMER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        productService.deleteById(id);
        return new ResponseEntity<>("Product deleted.", HttpStatus.ACCEPTED);
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