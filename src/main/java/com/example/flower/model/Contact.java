package com.example.flower.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "contacts")   // MongoDB collection
public class Contact {

    @Id
    private String id;   // MongoDB ObjectId (String)

    private String name;
    private String email;
    private String phone;
    private String message;

    private LocalDateTime createdAt;

    // ✅ Default constructor
    public Contact() {
        this.createdAt = LocalDateTime.now();  // auto timestamp
    }

    // ✅ Full constructor
    public Contact(String name, String email, String phone, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.createdAt = LocalDateTime.now();  // auto timestamp
    }

    // =========================
    // Getters & Setters
    // =========================

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}