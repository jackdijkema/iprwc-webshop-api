package dev.jacksd.iprwc.api.DTO;

import dev.jacksd.iprwc.api.model.Product;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTO {
    private Product product;
    private int quantity;
}