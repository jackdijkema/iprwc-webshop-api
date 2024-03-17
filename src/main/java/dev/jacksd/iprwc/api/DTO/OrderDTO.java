package dev.jacksd.iprwc.api.DTO;

import dev.jacksd.iprwc.api.model.OrderItem;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public class OrderDTO {
    Collection<OrderItemDTO> orderItems;
}