package dev.jacksd.iprwc.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String bio;

    @Column
    private String photo_url;

    public Product(String name, double price, String bio, String photo_url) {
        this.name = name;
        this.price = price;
        this.bio = bio;
        this.photo_url = photo_url;
    }

    public Product() {}
}
