package com.example.app.api;

import com.example.app.booking.BookingDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BookingApi {

    @PostMapping("/bookings")
    ResponseEntity<Void> bookingsPost(@RequestBody BookingDTO bookingDTO);

    @GetMapping("/bookings")
    ResponseEntity<List<BookingDTO>> bookingsGet(
            @RequestParam(required = false) Long propertyId,
            @RequestParam(required = false) Long userId);

    @GetMapping("/bookings/{id}")
    ResponseEntity<BookingDTO> bookingsIdGet(@PathVariable("id") Long id);

    @PutMapping("/bookings/{id}")
    ResponseEntity<BookingDTO> bookingsIdPut(
            @PathVariable("id") Long id,
            @RequestBody BookingDTO bookingDTO);

    @DeleteMapping("/bookings/{id}")
    ResponseEntity<Void> bookingsIdDelete(@PathVariable("id") Long id);
}
