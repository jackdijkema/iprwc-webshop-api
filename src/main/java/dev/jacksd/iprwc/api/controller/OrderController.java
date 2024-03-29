package dev.jacksd.iprwc.api.controller;

import dev.jacksd.iprwc.api.DTO.OrderDTO;
import dev.jacksd.iprwc.api.DTO.OrderItemDTO;
import dev.jacksd.iprwc.api.DTO.OrderResponse;
import dev.jacksd.iprwc.api.Service.OrderService;
import dev.jacksd.iprwc.api.Service.ProductService;
import dev.jacksd.iprwc.api.Service.UserService;
import dev.jacksd.iprwc.api.model.Order;
import dev.jacksd.iprwc.api.model.OrderItem;
import dev.jacksd.iprwc.api.model.Product;
import dev.jacksd.iprwc.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Optional<User> user = userService.getUserByEmail(authenticatedUserEmail);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticatedUserEmail.equals(user.get().getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<OrderItem> orderItems = new HashSet<>();
        Order order = new Order();
        order.setUser(user.get());

        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {

            Optional<Product> productOptional = productService.getProductById(orderItemDTO.getProduct().getId());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                System.out.println(product);

                OrderItem orderItem = new OrderItem();

                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(orderItemDTO.getQuantity());
                orderItems.add(orderItem);
            } else {
                return ResponseEntity.badRequest().body("Product with ID " + orderItemDTO.getProduct() + " not found");
            }
        }
        order.setOrderItems(orderItems);
        try {
            orderService.save(order);
            return new ResponseEntity<>("Order created. " + order.getId().toString(), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    private ResponseEntity<List<OrderResponse>> getOrdersByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Optional<User> user = userService.getUserByEmail(authenticatedUserEmail);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticatedUserEmail.equals(user.get().getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Order> orders = orderService.getAllOrdersByUser(user.get());

        List<OrderResponse> orderDTOS = new ArrayList<>();


        for (Order order : orders) {
            List<OrderItemDTO> orderItemDtos = order
                    .getOrderItems()
                    .stream()
                    .map(orderItem -> {
                        OrderItemDTO orderItemDTO = new OrderItemDTO();
                        orderItemDTO.setProduct(orderItem.getProduct());
                        orderItemDTO.setQuantity(orderItem.getQuantity());
                        return orderItemDTO;
                    }).toList();


            OrderResponse dto = new OrderResponse();

            dto.setId(order.getId());
            dto.setCreationDate(order.getCreationDate());
            dto.setOrderItems(orderItemDtos);
            orderDTOS.add(dto);
        }

        return ResponseEntity.ok(orderDTOS);
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Optional<User> user = userService.getUserByEmail(authenticatedUserEmail);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!authenticatedUserEmail.equals(user.get().getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Order> userOrders = orderService.getAllOrdersByUser(user.get());
        OrderResponse orderResponse = new OrderResponse();

        if (!userOrders.isEmpty()) {
            for (Order order : userOrders) {
                if (order.getId().equals(id)) {
                    List<OrderItemDTO> orderItemDtos = order.getOrderItems().stream()
                            .map(orderItem -> {
                                OrderItemDTO orderItemDTO = new OrderItemDTO();
                                orderItemDTO.setProduct(orderItem.getProduct());
                                orderItemDTO.setQuantity(orderItem.getQuantity());
                                return orderItemDTO;
                            }).toList();

                    orderResponse.setOrderItems(orderItemDtos);
                    orderResponse.setId(order.getId());
                    orderResponse.setTotalAmount(order.getTotalAmount());
                    orderResponse.setCreationDate(order.getCreationDate());
                    return ResponseEntity.ok(orderResponse);
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
