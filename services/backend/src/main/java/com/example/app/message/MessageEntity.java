package com.example.app.message;

import com.example.app.booking.BookingEntity;
import com.example.app.user.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MESSAGES")
public class MessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /* FK → BOOKINGS(id)  */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", nullable = false)
  private BookingEntity booking;

  /* FK → USERS(id) (sender) */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sender_id", nullable = false)
  private UserEntity sender;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(name = "sent_at", nullable = false)
  private LocalDateTime sentAt;

  public MessageEntity() {}

  public MessageEntity(
      Long id,
      BookingEntity booking,
      UserEntity sender,
      String content,
      LocalDateTime sentAt) {
    this.id = id;
    this.booking = booking;
    this.sender = sender;
    this.content = content;
    this.sentAt = sentAt;
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

  public UserEntity getSender() {
    return sender;
  }

  public void setSender(UserEntity sender) {
    this.sender = sender;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getSentAt() {
    return sentAt;
  }

  public void setSentAt(LocalDateTime sentAt) {
    this.sentAt = sentAt;
  }
}
