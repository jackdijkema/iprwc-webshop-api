package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTO {
    private UUID product;
    private int quantity;
}