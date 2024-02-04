package dev.jacksd.iprwc.api.repositories;

import dev.jacksd.iprwc.api.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> { }
