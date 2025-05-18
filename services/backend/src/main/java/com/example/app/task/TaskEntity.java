package com.example.app.task;

import com.example.app.booking.BookingEntity;
import com.example.app.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.example.app.task.TaskStatus;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id")
  private BookingEntity booking;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cleaner_id")
  private UserEntity cleaner;

  private String type;

  @Enumerated(EnumType.STRING)
  private TaskStatus status;
  private LocalDateTime due;

  public TaskEntity() {}

  public TaskEntity(
      Long id,
      BookingEntity booking,
      UserEntity cleaner,
      String type,
      TaskStatus status,
      LocalDateTime due) {
    this.id = id;
    this.booking = booking;
    this.cleaner = cleaner;
    this.type = type;
    this.status = status;
    this.due = due;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BookingEntity getBooking() {
    return booking;
  }

  public void setBooking(BookingEntity booking) {
    this.booking = booking;
  }

  public UserEntity getCleaner() {
    return cleaner;
  }

  public void setCleaner(UserEntity cleaner) {
    this.cleaner = cleaner;
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

  public LocalDateTime getDue() {
    return due;
  }

  public void setDue(LocalDateTime due) {
    this.due = due;
  }
}
