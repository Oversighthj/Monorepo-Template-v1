package com.example.app.message;

import com.example.app.api.MessageApi;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
import com.example.app.model.MessageDTO;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi {

  private final MessageService messageService;
  private final BookingService bookingService;
  private final UserRepository userRepository;

  @Override
  public ResponseEntity<Void> messagesPost(@Valid @RequestBody MessageDTO dto) {
    BookingEntity booking = bookingService.findById(dto.getBookingId());
    if (booking == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
    }
    UserEntity sender =
        userRepository
            .findById(dto.getSenderId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found"));

    MessageEntity entity =
        new MessageEntity(
            null,
            booking,
            sender,
            dto.getContent(),
            dto.getSentAt() != null ? dto.getSentAt().toLocalDateTime() : OffsetDateTime.now().toLocalDateTime());

    messageService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<MessageDTO>> messagesGet(Long bookingId) {
    List<MessageDTO> list =
        messageService.list(bookingId).stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

  private MessageDTO toDto(MessageEntity e) {
    MessageDTO d = new MessageDTO();
    d.setId(e.getId());
    d.setBookingId(e.getBooking().getId());
    d.setSenderId(e.getSender().getId());
    d.setContent(e.getContent());
    d.setSentAt(e.getSentAt().atOffset(OffsetDateTime.now().getOffset()));
    return d;
  }
}
