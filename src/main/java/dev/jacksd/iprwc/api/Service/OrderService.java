package dev.jacksd.iprwc.api.Service;

import dev.jacksd.iprwc.api.model.Order;
import dev.jacksd.iprwc.api.model.OrderItem;
import dev.jacksd.iprwc.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public double calculateTotalAmount(Set<OrderItem> products) {
        double totalAmount = 0;
             for (OrderItem product : products) {
                 totalAmount += product.getQuantity() * product.getProduct().getPrice();
             }
             return totalAmount;
    }

}
