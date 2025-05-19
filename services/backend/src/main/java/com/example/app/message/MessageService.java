package com.example.app.message;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository messageRepository;

  /* ---------- create ---------- */
  public MessageEntity create(MessageEntity entity) {
    return messageRepository.save(entity);
  }

  /* ---------- list (optional filter) ---------- */
  public List<MessageEntity> list(Long bookingId) {
    if (bookingId == null) {
      return messageRepository.findAll();
    }
    return messageRepository.findByBookingIdOrderBySentAtAsc(bookingId);
  }
}
