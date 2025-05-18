package com.example.app.model;

import jakarta.annotation.Generated;
import java.time.OffsetDateTime;

@Generated(value = "manual")
public class TaskDTO {
  private Long id;
  private Long bookingId;
  private Long cleanerId;
  private String type;
  private TaskStatus status;
  private OffsetDateTime due;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBookingId() {
    return bookingId;
  }

  public void setBookingId(Long bookingId) {
    this.bookingId = bookingId;
  }

  public Long getCleanerId() {
    return cleanerId;
  }

  public void setCleanerId(Long cleanerId) {
    this.cleanerId = cleanerId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public OffsetDateTime getDue() {
    return due;
  }

  public void setDue(OffsetDateTime due) {
    this.due = due;
  }
}
