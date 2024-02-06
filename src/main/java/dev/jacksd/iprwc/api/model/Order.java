package dev.jacksd.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
    Date orderDate;

    @Column
    double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public Order(User user, Set<OrderItem> orderItems) {
        this.orderDate = new Date();
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order() {
        this.orderDate = new Date();
    }

}