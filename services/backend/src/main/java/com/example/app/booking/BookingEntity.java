package com.example.app.booking;

import com.example.app.property.PropertyEntity;
import com.example.app.user.UserEntity;
import com.example.app.booking.BookingStatus;
import jakarta.persistence.Column;
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
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOKINGS")
public class BookingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "property_id")
  private PropertyEntity property;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  private LocalDateTime start;

  @Column(name = "end")
  private LocalDateTime end;

  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  public BookingEntity() {}

  public BookingEntity(
      Long id,
      PropertyEntity property,
      UserEntity user,
      LocalDateTime start,
      LocalDateTime end,
      BookingStatus status) {
    this.id = id;
    this.property = property;
    this.user = user;
    this.start = start;
    this.end = end;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PropertyEntity getProperty() {
    return property;
  }

  public void setProperty(PropertyEntity property) {
    this.property = property;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }

  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }
}
