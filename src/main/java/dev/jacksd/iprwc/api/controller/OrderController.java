package dev.jacksd.iprwc.api.controller;

import dev.jacksd.iprwc.api.Service.OrderService;
import dev.jacksd.iprwc.api.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/v1/orders")
@RestController
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        orderService.save(order);
    }
}
