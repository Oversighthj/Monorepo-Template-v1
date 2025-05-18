package com.example.app.api;

import com.example.app.model.BookingDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BookingApi {

    @PostMapping("/bookings")
    ResponseEntity<Void> createBooking(@RequestBody BookingDTO bookingDTO);

    @GetMapping("/bookings")
    ResponseEntity<List<BookingDTO>> getBookings(
            @RequestParam(value = "propertyId", required = false) Long propertyId,
            @RequestParam(value = "userId", required = false) Long userId);

    @GetMapping("/bookings/{id}")
    ResponseEntity<BookingDTO> getBooking(@PathVariable("id") Long id);

    @PutMapping("/bookings/{id}")
    ResponseEntity<BookingDTO> updateBooking(@PathVariable("id") Long id, @RequestBody BookingDTO bookingDTO);

    @DeleteMapping("/bookings/{id}")
    ResponseEntity<Void> deleteBooking(@PathVariable("id") Long id);
}
