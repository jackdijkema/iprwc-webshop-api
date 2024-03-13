package dev.jacksd.iprwc.api.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {
    UUID id;
    String name;
    Double price;
    String bio;
    String photoUrl;
}
