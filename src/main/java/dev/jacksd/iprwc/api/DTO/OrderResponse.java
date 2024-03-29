package dev.jacksd.iprwc.api.DTO;

import dev.jacksd.iprwc.api.model.Order;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class OrderResponse {
    UUID id;
    List<OrderItemDTO> orderItems;
    LocalDate creationDate;
    double totalAmount;
}