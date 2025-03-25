package com.code.mckvie.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders",schema = "ECommercedb")

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp orderDate;

    @Column(nullable = false)
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;

    public Orders() {}

    public Orders(Timestamp orderDate, double totalAmount, Users user) {
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    // Getters and Setters
}
