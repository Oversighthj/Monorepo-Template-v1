package com.example.app.booking;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
  List<BookingEntity> findByPropertyId(Long propertyId);

  List<BookingEntity> findByUserId(Long userId);

  List<BookingEntity> findByPropertyIdAndUserId(Long propertyId, Long userId);

  List<BookingEntity> findByPropertyIdAndStatus(Long propertyId, BookingStatus status);
}
