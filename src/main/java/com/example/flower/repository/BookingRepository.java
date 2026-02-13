package com.example.flower.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.flower.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
}