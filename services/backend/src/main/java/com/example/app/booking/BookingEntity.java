package com.example.app.booking;

import com.example.app.property.PropertyEntity;
import com.example.app.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

  @Column(name = "start")
  private LocalDateTime startAt;

  @Column(name = "end")
  private LocalDateTime endAt;

  private String status;

  public BookingEntity() {}

  public BookingEntity(
      Long id,
      PropertyEntity property,
      UserEntity user,
      LocalDateTime startAt,
      LocalDateTime endAt,
      String status) {
    this.id = id;
    this.property = property;
    this.user = user;
    this.startAt = startAt;
    this.endAt = endAt;
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
