package dev.jacksd.iprwc.api.Service;

import dev.jacksd.iprwc.api.model.Order;
import dev.jacksd.iprwc.api.model.User;
import dev.jacksd.iprwc.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> getOrderById(UUID id) {return  orderRepository.findById(id);
    }
}
