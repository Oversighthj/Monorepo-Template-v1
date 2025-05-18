package com.example.app.booking;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody BookingDTO dto) {
        try {
            bookingService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> list(
            @RequestParam(required = false) Long propertyId,
            @RequestParam(required = false) Long userId) {
        List<BookingDTO> dtos = bookingService.findAll(propertyId, userId).stream()
            .map(this::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> get(@PathVariable Long id) {
        BookingEntity entity = bookingService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> update(@PathVariable Long id, @Valid @RequestBody BookingDTO dto) {
        BookingEntity updated = bookingService.update(id, dto);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private BookingDTO toDto(BookingEntity entity) {
        BookingDTO dto = new BookingDTO();
        dto.setId(entity.getId());
        dto.setPropertyId(entity.getProperty().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setStart(entity.getStart());
        dto.setEnd(entity.getEnd());
        dto.setStatus(BookingStatus.valueOf(entity.getStatus()));
        return dto;
    }
}
