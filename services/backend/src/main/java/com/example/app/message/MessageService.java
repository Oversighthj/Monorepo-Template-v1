package com.example.app.message;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository messageRepository;

  public MessageEntity create(MessageEntity entity) {
    return messageRepository.save(entity);
  }

  public List<MessageEntity> list(Long bookingId) {
    if (bookingId == null) {
      return messageRepository.findAllByOrderBySentAtAsc();
    }
    return messageRepository.findByBookingIdOrderBySentAtAsc(bookingId);
  }
}
