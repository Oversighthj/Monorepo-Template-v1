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
          + "AND b.startAt < :endAt "
          + "AND b.endAt > :startAt")
  List<BookingEntity> findOverlapping(
      @Param("propertyId") Long propertyId,
      @Param("startAt") LocalDateTime startAt,
      @Param("endAt") LocalDateTime endAt,
      @Param("excludeId") Long excludeId);

  @Query(
      "SELECT b FROM BookingEntity b "
          + "WHERE (:propertyId IS NULL OR b.property.id = :propertyId) "
          + "AND (:userId IS NULL OR b.user.id = :userId) "
          + "ORDER BY b.id")
  List<BookingEntity> findByFilters(
      @Param("propertyId") Long propertyId, @Param("userId") Long userId);
}
