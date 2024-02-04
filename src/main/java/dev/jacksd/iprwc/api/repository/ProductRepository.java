package dev.jacksd.iprwc.api.repository;

import dev.jacksd.iprwc.api.model.Product;
import dev.jacksd.iprwc.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> { }
