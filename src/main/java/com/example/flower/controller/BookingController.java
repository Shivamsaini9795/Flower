package com.example.flower.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.example.flower.model.Booking;
import com.example.flower.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") // allow frontend (React / Admin Panel)
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /** ✅ POST - Create new booking (frontend form submission) */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

    /** ✅ GET - Fetch all bookings (for admin dashboard) */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /** ✅ GET - Fetch single booking by ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.status(404).body("❌ Booking not found with ID: " + id);
        }
    }

    /** ✅ PUT - Update booking details */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        try {
            Booking booking = bookingService.updateBooking(id, updatedBooking);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /** ✅ DELETE - Remove booking (for admin delete button) */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.ok("✅ Booking deleted successfully (ID: " + id + ")");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
