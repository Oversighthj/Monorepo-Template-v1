package com.example.app.model;

import jakarta.annotation.Generated;
import java.time.OffsetDateTime;

@Generated(value = "manual")
public class MessageDTO {
  private Long id;
  private Long bookingId;
  private Long senderId;
  private String content;
  private OffsetDateTime sentAt;

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

  public Long getSenderId() {
    return senderId;
  }

  public void setSenderId(Long senderId) {
    this.senderId = senderId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public OffsetDateTime getSentAt() {
    return sentAt;
  }

  public void setSentAt(OffsetDateTime sentAt) {
    this.sentAt = sentAt;
  }
}
