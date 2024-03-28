package dev.jacksd.iprwc.api.Service;

import dev.jacksd.iprwc.api.model.Order;
import dev.jacksd.iprwc.api.model.OrderItem;
import dev.jacksd.iprwc.api.model.User;
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

    public List<Order> getAllOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public double getTotalOrderPrice(Order order) {
        double totalPrice = 0.0;
        for (OrderItem orderItem : order.getOrderItems()) {
            totalPrice += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        return totalPrice;
    }
}
