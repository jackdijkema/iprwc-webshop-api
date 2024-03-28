package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class OrderDTO {
    List<OrderItemDTO> orderItems;
}