package com.example.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flower.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
