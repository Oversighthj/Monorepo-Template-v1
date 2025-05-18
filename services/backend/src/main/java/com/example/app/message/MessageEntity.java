package com.example.app.message;

import com.example.app.user.UserEntity;
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
@Table(name = "MESSAGES")
public class MessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_id")
  private UserEntity from;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_id")
  private UserEntity to;

  private String body;
  private LocalDateTime ts;

  public MessageEntity() {}

  public MessageEntity(Long id, UserEntity from, UserEntity to, String body, LocalDateTime ts) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.body = body;
    this.ts = ts;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserEntity getFrom() {
    return from;
  }

  public void setFrom(UserEntity from) {
    this.from = from;
  }

  public UserEntity getTo() {
    return to;
  }

  public void setTo(UserEntity to) {
    this.to = to;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDateTime getTs() {
    return ts;
  }

  public void setTs(LocalDateTime ts) {
    this.ts = ts;
  }
}
