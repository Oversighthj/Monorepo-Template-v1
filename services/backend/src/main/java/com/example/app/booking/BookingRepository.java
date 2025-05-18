package com.example.app.booking;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

  List<BookingEntity> findByPropertyId(Long propertyId);

  List<BookingEntity> findByUserId(Long userId);

  List<BookingEntity> findByPropertyIdAndUserId(Long propertyId, Long userId);

  @Query("""
      SELECT b FROM BookingEntity b
      WHERE b.property.id = :propertyId
        AND b.status = 'CONFIRMED'
        AND (:start < b.end AND :end > b.start)
      """)
  List<BookingEntity> findOverlapping(@Param("propertyId") Long propertyId,
                                      @Param("start") LocalDateTime start,
                                      @Param("end") LocalDateTime end);
}
