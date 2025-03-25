package com.code.mckvie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category",schema = "ECommercedb") // Maps to database table 'category'
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates primary key
    private Long id;

    @Column(nullable = false, unique = true) // Enforces unique name
    private String name;

    @Column
    private String description;

    // Default constructor (required by Hibernate)
    public Category() {
    }

    // Parameterized constructor
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
