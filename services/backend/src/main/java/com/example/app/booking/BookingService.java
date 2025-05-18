package com.example.app.booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;

  public BookingService(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public BookingEntity create(BookingEntity booking) {
    return bookingRepository.save(booking);
  }

  public BookingEntity findById(Long id) {
    Optional<BookingEntity> entity = bookingRepository.findById(id);
    return entity.orElse(null);
  }


  public List<BookingEntity> findOverlapping(Long propertyId, LocalDateTime startAt, LocalDateTime endAt, Long excludeId) {

  public List<BookingEntity> findOverlapping(
      Long propertyId, LocalDateTime startAt, LocalDateTime endAt, Long excludeId) {

    return bookingRepository.findOverlapping(propertyId, startAt, endAt, excludeId);
  }

  public BookingEntity update(BookingEntity booking) {
    if (booking.getId() == null) {
      throw new IllegalArgumentException("booking id required");
    }
    Optional<BookingEntity> existingOpt = bookingRepository.findById(booking.getId());
    if (existingOpt.isEmpty()) {
      return null;
    }
    BookingEntity existing = existingOpt.get();

    boolean startChanged = booking.getStartAt() != null && !booking.getStartAt().equals(existing.getStartAt());
    boolean endChanged = booking.getEndAt() != null && !booking.getEndAt().equals(existing.getEndAt());

    boolean startChanged =
        booking.getStartAt() != null && !booking.getStartAt().equals(existing.getStartAt());
    boolean endChanged =
        booking.getEndAt() != null && !booking.getEndAt().equals(existing.getEndAt());

    Long existingPropId = existing.getProperty() != null ? existing.getProperty().getId() : null;
    Long newPropId = booking.getProperty() != null ? booking.getProperty().getId() : null;
    boolean propertyChanged = (existingPropId != null ? !existingPropId.equals(newPropId) : newPropId != null);

    if (startChanged || endChanged || propertyChanged) {

      List<BookingEntity> overlaps = findOverlapping(newPropId, booking.getStartAt(), booking.getEndAt(), booking.getId());

      List<BookingEntity> overlaps =
          findOverlapping(newPropId, booking.getStartAt(), booking.getEndAt(), booking.getId());

      if (!overlaps.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Booking overlaps");
      }
    }

    existing.setProperty(booking.getProperty());
    existing.setUser(booking.getUser());
    existing.setStartAt(booking.getStartAt());
    existing.setEndAt(booking.getEndAt());
    existing.setStatus(booking.getStatus());

    return bookingRepository.save(existing);
  }
}
