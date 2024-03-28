package dev.jacksd.iprwc.api.repository;

import dev.jacksd.iprwc.api.model.Order;
import dev.jacksd.iprwc.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {


    List<Order> findByUser(User user);

}
