package com.example.flower.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.flower.model.Booking;
import com.example.flower.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        existingBooking.setName(updatedBooking.getName());
        existingBooking.setPhone(updatedBooking.getPhone());
        existingBooking.setEventDate(updatedBooking.getEventDate());
        existingBooking.setEventType(updatedBooking.getEventType());
        existingBooking.setMessage(updatedBooking.getMessage());

        return bookingRepository.save(existingBooking);
    }
}
