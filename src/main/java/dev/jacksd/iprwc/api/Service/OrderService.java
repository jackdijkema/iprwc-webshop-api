package dev.jacksd.iprwc.api.Service;

import dev.jacksd.iprwc.api.model.Order;
import dev.jacksd.iprwc.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        order.setCreationDate(LocalDate.now());
        orderRepository.save(order);
    }

}
