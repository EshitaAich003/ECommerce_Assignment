package com.code.mckvie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_details",schema = "ECommercedb") // Ensure table name is specified
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;  // Ensure this is correctly referenced

    public OrderDetails() {}

    public OrderDetails(int quantity, double unitPrice, Orders order, Product product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;
        this.product = product;
    }

    // Getters and Setters
}
