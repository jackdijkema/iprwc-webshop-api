package dev.jacksd.iprwc.api.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "_orders")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column
    LocalDate creationDate;

    @Column
    double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public Order(User user, Set<OrderItem> orderItems) {
        this.creationDate = LocalDate.now();
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order() {
        this.creationDate = LocalDate.now();
    }

}