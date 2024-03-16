package dev.jacksd.iprwc.api.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column
    String name;

    @Column
    double price;

    @Column
    String bio;

    @Column
    String photoUrl;
    
    @Column
    String artist;

    public Product(String name, double price, String bio, String photoUrl, String artist) {
        this.name = name;
        this.price = price;
        this.bio = bio;
        this.artist = artist;
        this.photoUrl = photoUrl;
    }
}