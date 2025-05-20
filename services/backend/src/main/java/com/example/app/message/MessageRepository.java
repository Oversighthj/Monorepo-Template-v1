package com.example.app.message;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

  List<MessageEntity> findByBookingIdOrderBySentAtAsc(Long bookingId);

  /** Returns all messages ordered by sentAt ascending. */
  List<MessageEntity> findAllByOrderBySentAtAsc();
}
