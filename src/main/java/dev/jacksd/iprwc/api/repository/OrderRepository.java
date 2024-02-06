package dev.jacksd.iprwc.api.repository;

import dev.jacksd.iprwc.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
