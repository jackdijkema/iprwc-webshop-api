package dev.jacksd.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "_orders_item")
@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude="order")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column
    int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;


    OrderItem(Order order, Product product, Integer quantity) {
        order.setCreationDate(LocalDate.now());
        this.quantity = quantity;
        this.product = product;
    }

}
