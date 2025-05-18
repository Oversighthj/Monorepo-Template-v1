package com.example.app.booking;

import java.time.LocalDateTime;

public class BookingDTO {
  private Long id;
  private Long propertyId;
  private Long userId;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(Long propertyId) {
    this.propertyId = propertyId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LocalDateTime getStartAt() {
    return startAt;
  }

  public void setStartAt(LocalDateTime startAt) {
    this.startAt = startAt;
  }

  public LocalDateTime getEndAt() {
    return endAt;
  }

  public void setEndAt(LocalDateTime endAt) {
    this.endAt = endAt;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
