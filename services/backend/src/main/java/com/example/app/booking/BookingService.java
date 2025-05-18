package com.example.app.booking;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingStatus;
import com.example.app.booking.OverlapException;

@Service
public class BookingService {
  private final BookingRepository bookingRepository;

  public BookingService(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public BookingEntity create(BookingEntity booking) {
    checkOverlap(booking, null);
    return bookingRepository.save(booking);
  }

  public BookingEntity update(BookingEntity booking) {
    checkOverlap(booking, booking.getId());
    return bookingRepository.save(booking);
  }

  public List<BookingEntity> findAll(Optional<Long> propertyId, Optional<Long> userId) {
    if (propertyId.isPresent() && userId.isPresent()) {
      return bookingRepository.findByPropertyIdAndUserId(propertyId.get(), userId.get());
    }
    if (propertyId.isPresent()) {
      return bookingRepository.findByPropertyId(propertyId.get());
    }
    if (userId.isPresent()) {
      return bookingRepository.findByUserId(userId.get());
    }
    return bookingRepository.findAll();
  }

  public BookingEntity findById(Long id) {
    return bookingRepository.findById(id).orElse(null);
  }

  public void delete(Long id) {
    bookingRepository.deleteById(id);
  }

  private void checkOverlap(BookingEntity booking, Long ignoreId) {
    List<BookingEntity> existing =
        bookingRepository.findByPropertyIdAndStatus(booking.getProperty().getId(), BookingStatus.CONFIRMED);
    for (BookingEntity other : existing) {
      if (ignoreId != null && other.getId().equals(ignoreId)) {
        continue;
      }
      boolean overlaps =
          booking.getStart().isBefore(other.getEnd()) && booking.getEnd().isAfter(other.getStart());
      if (overlaps) {
        throw new OverlapException();
      }
    }
  }
}
