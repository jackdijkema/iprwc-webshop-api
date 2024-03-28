package dev.jacksd.iprwc.api.DTO;

import dev.jacksd.iprwc.api.model.Order;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
public class OrderResponse {
    List<Order> orderItems;
    LocalDate creationDate;
    double totalAmount;
}