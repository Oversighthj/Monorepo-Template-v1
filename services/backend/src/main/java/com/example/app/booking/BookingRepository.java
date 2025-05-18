package com.example.app.booking;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

  @Query(
      "SELECT b FROM BookingEntity b "
          + "WHERE b.property.id = :propertyId "
          + "AND (:excludeId IS NULL OR b.id <> :excludeId) "
          + "AND b.start < :end "
          + "AND b.end > :start")
  List<BookingEntity> findOverlapping(
      @Param("propertyId") Long propertyId,
      @Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end,
      @Param("excludeId") Long excludeId);
}
