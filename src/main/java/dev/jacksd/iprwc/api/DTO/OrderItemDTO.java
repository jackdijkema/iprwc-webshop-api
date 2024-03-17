package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTO {
    private int quantity;
    private UUID product;
}