package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
public class OrderDTO {
    UUID id;
    Date orderDate;
    double totalPrice;
    private Set<ProductDTO> products;
}