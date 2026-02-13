package com.example.flower.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

@Document(collection = "bookings")   // MongoDB collection
public class Booking {

    @Id
    private String id;   // MongoDB uses String ObjectId

    private String name;
    private String phone;

    @JsonProperty("date")
    private String eventDate;

    @JsonProperty("eventType")
    private String eventType;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    // ✅ Default constructor
    public Booking() {
        this.createdAt = LocalDateTime.now();  // manually set timestamp
    }

    // ✅ Full constructor
    public Booking(String name, String phone, String eventDate, String eventType, String message) {
        this.name = name;
        this.phone = phone;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.message = message;
        this.createdAt = LocalDateTime.now();  // auto time set
    }

    // =========================
    // Getters & Setters
    // =========================
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}